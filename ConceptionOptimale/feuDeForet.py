#!python

from typing import List
import numpy as np
import matplotlib as mpl
from numpy.random import rand
import os
from scipy import optimize
from scipy.optimize import minimize, LinearConstraint, Bounds
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
from mpl_toolkits.axes_grid1 import make_axes_locatable
from time import perf_counter
from scipy.optimize.optimize import rosen
from dataclasses import dataclass
import numpy.linalg as LA
from scipy.linalg import sqrtm
from pyswarm import pso
import multiprocessing
def unpacking_apply_along_axis(all_args):
    (func1d, axis, arr, args, kwargs) = all_args
    return np.apply_along_axis(func1d, axis, arr, *args, **kwargs)
def parallel_apply_along_axis(func1d, axis, arr, *args, **kwargs):
    """
    Like numpy.apply_along_axis(), but takes advantage of multiple
    cores.
    """        
    # Effective axis where apply_along_axis() will be applied by each
    # worker (any non-zero axis number would work, so as to allow the use
    # of `np.array_split()`, which is only done on axis 0):
    effective_axis = 1 if axis == 0 else axis
    if effective_axis != axis:
        arr = arr.swapaxes(axis, effective_axis)
    n_split = min(arr.shape[axis],multiprocessing.cpu_count())
    # Chunks for the mapping (only a few chunks):
    chunks = [(func1d, effective_axis, sub_arr, args, kwargs)
              for sub_arr in np.array_split(arr, n_split)]

    pool = multiprocessing.Pool()
    individual_results = pool.map(unpacking_apply_along_axis, chunks)
    # Freeing the workers:
    pool.close()
    pool.join()

    return np.concatenate(individual_results)
np.random.seed(7)


@dataclass()
class Result:
    x: float
    feval: float
    n: int
    n_eval: int
    std: float
    f_vec: List[float]
    n_eval_vec: List[int]

    def __init__(self, x, feval, n, n_eval, std, f_vec,n_eval_vec) -> None:
        self.x = x
        self.n = n
        self.n_eval = n_eval
        self.feval = feval
        self.std = std
        self.f_vec = f_vec
        self.n_eval_vec = n_eval_vec

    def __repr__(self) -> str:
        return self.__dict__.__str__()


def progress(i, n):
    i += 1
    k = int(i/n*20)
    print(f'\rProgression:[{k*"#"}{(20-k)*" "}] [{(i/n*100):.0f} %]',
          end='' if i != n else "\n", flush=True)


def randomC(n_circ, a, b):
    R = np.zeros((ns, ns))
    for _ in range(n_circ):
        x, y, r, v = rand(), rand(), rand()*0.3, (b-a)*rand()+a
        R += ((X-x)**2+(Y-y)**2 <= r**2)*v
    return R


ns = 80
nt = 300
mu = 5e-3
delta = 1/ns
x = np.linspace(0, 1, ns)
y = np.linspace(0, 1, ns)
X, Y = np.meshgrid(x, y)
u = np.cos(np.pi*Y)
v = 0.6*np.sin(np.pi/2*X)
V = np.array([u, v])
c = np.max((u**2+v**2)**(1/2))
dt = 1/8*min(delta**2/(mu), c/delta)
C0 = 5+randomC(3, -5, 5)
T0 = (((X-0.1)**2+(Y-0.1)**2) <= 0.0001)*10

def grad(T, V, n):
    mask = (V[0, 1:-1, 1:-1] <= 0)
    mask2 = (V[1, 1:-1, 1:-1] <= 0)
    grad = np.zeros((2, ns-2, ns-2))
    grad[0][mask] = (T[n, 1:-1, 2:][mask]-T[n, 1:-1, 1:-1][mask])/delta
    grad[0][np.logical_not(mask)] = (
        T[n, 1:-1, 1:-1][np.logical_not(mask)]-T[n, 1:-1, :-2][np.logical_not(mask)])/delta
    grad[1][mask2] = (T[n, 2:, 1:-1][mask2]-T[n, 1:-1, 1:-1][mask2])/delta
    grad[1][np.logical_not(mask2)] = (
        T[n, 1:-1, 1:-1][np.logical_not(mask2)]-T[n, :-2, 1:-1][np.logical_not(mask2)])/delta

    return grad


def laplacien(T, n):
    return (T[n, 2:, 1:-1]+T[n, :-2, 1:-1]+T[n, 1:-1, 2:]+T[n, 1:-1, :-2]-4*T[n, 1:-1, 1:-1])/delta**2


@np.vectorize
def R(T, C):
    if T >= 0.05 and C >= 0:
        return 10
    elif T >= 0.05 and C < 0:
        return -5
    elif T < 0.05:
        return 0


@np.vectorize
def R2(T):
    return int(T > 0.05)*(-100)


def simu(xmin, xmax, ymin, ymax, show_bar=False, info=False):
    start = perf_counter()
    T = np.zeros((nt, ns, ns))
    C = np.zeros((nt, ns, ns))
    firebreak = np.logical_and(np.logical_and(
        (X >= xmin), (X <= xmax)), np.logical_and((Y >= ymin), (Y <= ymax)))
    C[0] = np.maximum(C0-10*firebreak, 0)
    T[0] = T0
    for n in range(nt-1):
        if show_bar:
            progress(n, nt-1)
        T[n+1, 1:-1, 1:-1] = T[n, 1:-1, 1:-1]+dt*(mu*laplacien(T, n)-np.einsum(
            "ijk,ijk->jk", V[:, 1:-1, 1:-1], grad(T, V, n))+R(T[n, 1:-1, 1:-1], C[n, 1:-1, 1:-1])*T[n, 1:-1, 1:-1])
        T[n+1, :, 0] = T[n+1, :, 1]
        T[n+1, :, -1] = T[n+1, :, -2]
        T[n+1, 0, :] = T[n+1, 1, :]
        T[n+1, -1, :] = T[n+1, -2, :]
        C[n+1, :, :] = C[n, :, :]+dt*R2(T[n])
    if info:
        print(f"Time taken: {perf_counter()-start:.2f} s")
    return C, T


def anim2d(C, T):
    fig, axs = plt.subplots(ncols=2)
    norm = mpl.colors.Normalize(vmin=0, vmax=0.2)
    norm2 = mpl.colors.Normalize(vmin=0, vmax=10)

    im1 = axs[0].imshow(T[0], cmap='jet', norm=norm, extent=[0, 1, 1, 0])
    axs[0].invert_yaxis()
    plt.colorbar(im1, pad=0.1, ax=axs[0])

    im2 = axs[1].imshow(C[0], cmap='Greens', norm=norm2, extent=[0, 1, 1, 0])
    axs[1].invert_yaxis()
    plt.colorbar(im2, pad=0.1, ax=axs[1])

    def animate(i):
        Ti = T[i]
        Ci = C[i]
        im1.set_data(Ti)
        im2.set_data(Ci)
        axs[0].set_title(f"Température Frame {i}")
        axs[1].set_title(f"Végétation Frame {i}")

    animation = FuncAnimation(fig, animate, frames=nt-1, interval=100)
    # plt.show()
    return animation

def plot(C):
    norm2 = mpl.colors.Normalize(vmin=0, vmax=10)
    ax = plt.axes()
    im2 = plt.imshow(C[-1], cmap='Greens', norm=norm2, extent=[0, 1, 1, 0])
    ax.invert_yaxis()
    ax.set_title("Végétation au temps final")
    plt.colorbar(im2, pad=0.1, ax=ax)
    plt.show()

def std(x):
    xg = np.mean(x)
    return np.sqrt(np.mean(np.linalg.norm(x-xg)**2))


def NelderMead(f, x0, step=0.1, alpha=1, gamma=2, rho=1/2, sigma=1/2, eps=1e-8, nmax=100, disp=False, hist=False):
    f_vec = []
    dim = len(x0)
    def f2(x): return np.apply_along_axis(f, 1, x)
    x = np.zeros((dim+1, dim))
    x[0] = x0
    x[1:] = x0+np.eye(dim, dim)*step
    n = 0
    n_eval = 0
    fx = f2(x).reshape(-1,)
    n_eval += len(x)
    sort = np.argsort(fx)
    x = x[sort]
    fx = fx[sort]
    while n < nmax and std(x) >= eps:
        n += 1
        if disp:
            print(fx[0], x[0])
        if hist:
            f_vec.append(fx[0])
        xg = np.mean(x[:-1], axis=0)
        xr = xg+alpha*(xg-x[-1])
        fr = f(xr)
        n_eval += 1
        if fx[0] < fr and fr < fx[-2]:
            x[-1] = xr
        elif fr < fx[0]:
            xe = xg+gamma*(xg-x[-1])
            n_eval += 1
            if f(xe) < fr:
                x[-1] = xe
            else:
                x[-1] = xr
        elif fr >= fx[-2]:
            xc = xg+rho*(x[-1]-xg)
            n_eval += 1
            if f(xc) < fx[-1]:
                x[-1] = xc
        else:
            x = x[0]+sigma*(x-x[0])

        fx = f2(x)
        n_eval += len(x)
        sort = np.argsort(fx)
        x = x[sort]
        fx = fx[sort]
        n += 1
    return Result(x[0], fx[0], n, n_eval, std(x), f_vec)


def Torczon(f, x0, step=0.1, alpha=1, gamma=2, beta=1/2, eps=1e-8, nmax=100, disp=False, hist=False):
    f_vec = []
    n_eval_vec = []
    dim = len(x0)
    n_eval = 0
    # def f2(x): return np.apply_along_axis(f, 1, x)
    x = np.zeros((dim+1, dim))
    x[0] = x0
    x[1:] = x0+np.eye(dim, dim)*step
    fx = parallel_apply_along_axis(f,1,x)
    n_eval += len(x)
    sort = np.argsort(fx)
    x = x[sort]
    fx = fx[sort]
    n = 1
    while n < nmax and std(x) >= eps:
        if disp:
            print(f"\x1b[33m"+str(n)+"\x1b[0m", fx[0], x[0], f"σ = {std(x)}")
        if hist:
            f_vec.append(fx[0])
            n_eval_vec.append(n_eval)
        x2 = np.zeros(x.shape)
        x2 = (1+alpha)*x[0]-alpha*x
        min2 = np.min(parallel_apply_along_axis(f,1,x2[1:]))
        n_eval += len(x2[1:])
        if min2 < fx[0]:
            x = gamma*x2+(1-gamma)*x
        else:
            x = beta*x2+(1-beta)*x[0]
        fx = parallel_apply_along_axis(f,1,x)
        n_eval += len(x)
        sort = np.argsort(fx)
        x = x[sort]
        fx = fx[sort]
        n += 1
    return Result(x[0], fx[0], n, n_eval, std(x), f_vec,n_eval_vec)


def muLambda(f, x0, sigma0, mu, lamb, tau, nmax=100, eps=1e-8, disp=False, hist=False, version="comma"):
    assert lamb > mu
    f_vec = []
    n_eval_vec = []
    n_eval = 0
    d = len(x0)
    # def f2(x): return np.apply_along_axis(f, 1, x)
    n = 1
    xbar = x0
    best = x0
    f_best = f(x0)
    n_eval += 1
    if hist:
        f_vec.append(f_best)
        n_eval_vec.append(n_eval)
    sigma_bar = sigma0
    x = np.array([x0])
    sigma = np.array([sigma0])
    while n < nmax and sigma_bar > eps:
        sigma_temp = sigma_bar*np.exp(tau*np.random.normal(size=lamb))
        cov = np.diag(sigma_temp**2)
        if version == "comma":
            x = xbar + \
                np.random.multivariate_normal(np.zeros((lamb,)), cov, size=d).T
            sigma = sigma_temp
        elif version == "plus":
            x = np.append(x, xbar +
                          np.random.multivariate_normal(np.zeros((lamb,)), cov, size=d).T, axis=0)
            sigma = np.append(sigma, sigma_temp, axis=0)
        fx = parallel_apply_along_axis(f,1,x)
        n_eval += len(x)
        sort = np.argsort(fx)
        x = x[sort]
        fx = fx[sort]
        if fx[0] < f_best:
            best = x[0]
            f_best = fx[0]
        sigma = sigma[sort]
        x = x[:mu]
        sigma = sigma[:mu]
        xbar = np.mean(x, axis=0)
        sigma_bar = np.mean(sigma)
        if hist:
            f_vec.append(f_best)
            n_eval_vec.append(n_eval)
        if disp:
            def my_round(x, pres=3): return np.round(x*10**pres)/10**pres
            print("\x1b[33m"+str(n)+"\x1b[0m", my_round(f_best), my_round(best),
                  my_round(f(xbar)), my_round(xbar), f"σ = {my_round(sigma_bar)}")
        n += 1
    return Result(best, f_best, n, n_eval, sigma_bar, f_vec,n_eval_vec)


def cost_func(xmin, xmax, ymin, ymax):
    C, T = simu(xmin, xmax, ymin, ymax)
    f = np.mean(C0-C[-1])+100.0*np.float64(ymin < 0.2 or ymax > 1 or xmin <
                               0 or xmax > 1 or xmax < xmin or ymax < ymin)+(xmax-xmin)*(ymax-ymin)
    return f

def cost_func_unpenalized(xmin,xmax,ymin,ymax):
    C, T = simu(xmin, xmax, ymin, ymax)
    f = np.mean(C0-C[-1])+(xmax-xmin)*(ymax-ymin)
    return f

def cost_func_noisy(xmin,xmax,ymin,ymax,delta=0.025):
    xmin = xmin+delta*np.random.rand()
    ymin = ymin+delta*np.random.rand()
    xmax = xmax+delta*np.random.rand()
    ymax = ymax+delta*np.random.rand()
    return cost_func_unpenalized(xmin,xmax,ymin,ymax)

def cost_func_MC(xmin,xmax,ymin,ymax,n=10):
    x_mc = np.array(n*[[xmin,xmax,ymin,ymax]])
    y_mc = parallel_apply_along_axis(J_noisy,1,x_mc)
    return np.mean(y_mc)

def cost_func2(xmin, xmax, ymin, ymax, w=1/2):
    C, T = simu(xmin, xmax, ymin, ymax)
    n_half = int(0.5/delta)
    f = (1-w)*np.mean(C0[:, n_half:]-C[-1, :, n_half:])+w*np.mean(C0[:, :n_half]-C[-1, :, :n_half]
                                                                  )+100*(ymin < 0.2 or ymax > 1 or xmin < 0 or xmax > 1 or xmax < xmin or ymax < ymin)
    return f


def J(x): return cost_func(*x)
def J_noisy(x):
    return cost_func_noisy(*x)
def J2(x): return cost_func2(*x, w=1/4)

if __name__=="__main__":
    x0 = [0.3, 0.5, 0.3, 0.5]
    print(cost_func_MC(*x0))

    # mu2 = 5
    # lamb = 10
    # sigma0 = 0.1
    # tau = 0.1
    # # C,T = simu(*x0)
    # # anim2d(C,T)
    # res = Torczon(J2,x0,eps=1e-4,disp=1,nmax=50,hist=True)
    # res2 = muLambda(J2, x0, sigma0, mu2, lamb, tau, eps=1e-4,
    #             disp=1,hist=True, nmax=50, version="comma")
    # C1, T1 = simu(*(res.x))
    # C2,T2 = simu(*(res2.x))
    # plot(C1)
    # plot(C2)
    # plt.plot(res.n_eval_vec,res.f_vec,label="Torczon")
    # plt.plot(res2.n_eval_vec,res2.f_vec,label="$(\mu,\lambda)$-ES")
    # plt.legend()
    # plt.xlabel("Nombre de simulations")
    # plt.ylabel("Valeur du critère")
    # plt.title(f"Comparaison entre Torczon et $(\mu,\lambda)$-ES avec $\lambda={lamb}$, $\mu={mu2}$, $\sigma_0={sigma0}$ et $\\tau={tau}$")
    # plt.show()

    # optim = cma.CMAEvolutionStrategy(x0,sigma0)
    # res = optim.optimize(J2,maxfun=400,verb_disp=True,n_jobs=-1).result[0]
