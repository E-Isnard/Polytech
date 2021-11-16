#!python

from typing import List
import numpy as np
import matplotlib as mpl
from numpy.random import rand
import os
from scipy.optimize import minimize, LinearConstraint, Bounds
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
from mpl_toolkits.axes_grid1 import make_axes_locatable
from time import perf_counter
import pyswarm as ps
from scipy.optimize.optimize import rosen
from dataclasses import dataclass
np.random.seed(7)
@dataclass()
class Result:
    x: float
    feval: float
    n: int
    std: float
    f_vec: List[float]

    def __init__(self, x, feval, n, std,f_vec) -> None:
        self.x = x
        self.n = n
        self.feval = feval
        self.std = std
        self.f_vec = f_vec

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
C0 = 5+randomC(3,-5,5)
T0 = (((X-0.1)**2+(Y-0.1)**2) <= 0.0001)*10
# norm2 = mpl.colors.Normalize(vmin=0, vmax=10)
# img = plt.imshow(C0,cmap="Greens",norm=norm2,extent=[0, 1, 1, 0])
# plt.colorbar(img,pad=0.1)
# plt.title("Exemple de distribution initiale pour la végétation")
# plt.xlabel("x")
# plt.ylabel("y")
# plt.savefig("C_0.png")
# plt.show()


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
    norm = mpl.colors.Normalize(vmin=0, vmax=1)
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


    animation = FuncAnimation(fig, animate, frames=nt-1, interval=1)

    plt.show()


def cost_func(xmin, xmax, ymin, ymax):
    C, T = simu(xmin, xmax, ymin, ymax)
    f = np.mean(C0-C[-1])+100*(ymin<0.2 or ymax>1 or xmin<0 or xmax>1 or xmax<xmin or ymax<ymin)
    # print(f, xmin, xmax, ymin, ymax)
    return f


def J(x): return cost_func(*x)

@np.vectorize
def J2(x): return cost_func(0.2,x,0.3,0.5)

def std(x):
    xg = np.mean(x)
    return np.sqrt(np.mean(np.linalg.norm(x-xg)**2))

def NelderMead(f, x0, step=0.1, alpha=1, gamma=2, rho=1/2, sigma=1/2, eps=1e-8, nmax=100, disp=False,hist=False):
    f_vec = []
    dim = len(x0)
    def f2(x): return np.apply_along_axis(f, 1, x)
    x = np.zeros((dim+1, dim))
    x[0] = x0
    x[1:] = x0+np.eye(dim, dim)*step
    n = 0
    fx = f2(x).reshape(-1,)
    sort = np.argsort(fx)
    x = x[sort]
    fx = fx[sort]
    while n<nmax and std(x)>=eps:
        n+=1
        if disp:
            print(fx[0], x[0])
        if hist:
            f_vec.append(fx[0])
        xg = np.mean(x[:-1], axis=0)
        xr = xg+alpha*(xg-x[-1])
        fr = f(xr)
        if fx[0] < fr and fr < fx[-2]:
            x[-1] = xr
        elif fr < fx[0]:
            xe = xg+gamma*(xg-x[-1])
            if f(xe) < fr:
                x[-1] = xe
            else:
                x[-1] = xr
        elif fr >= fx[-2]:
            xc = xg+rho*(x[-1]-xg)
            if f(xc) < fx[-1]:
                x[-1] = xc
        else:
            x = x[0]+sigma*(x-x[0])
    
        fx = f2(x)
        sort = np.argsort(fx)
        x = x[sort]
        fx = fx[sort]
        n+=1
    return Result(x[0], fx[0], n, std(x),f_vec)


def Torczon(f, x0, step=0.1, alpha=1, gamma=2, beta=1/2, eps=1e-8, nmax=100, disp=False,hist=False):
    f_vec = []
    dim = len(x0)
    def f2(x): return np.apply_along_axis(f, 1, x)
    x = np.zeros((dim+1, dim))
    x[0] = x0
    x[1:] = x0+np.eye(dim, dim)*step
    fx = f2(x)
    sort = np.argsort(fx)
    x = x[sort]
    fx = fx[sort]
    n=0
    while n<nmax and std(x)>=eps:
        if disp:
            print(fx[0], x[0])
        if hist:
            f_vec.append(fx[0])
        x2 = np.zeros(x.shape)
        x2 = (1+alpha)*x[0]-alpha*x
        min2 = np.min(f2(x2[1:]))
        if min2 < fx[0]:
            x = gamma*x2+(1-gamma)*x
        else:
            x = beta*x2+(1-beta)*x[0]
        fx = f2(x)
        sort = np.argsort(fx)
        x = x[sort]
        fx = fx[sort]
        n+=1
    return Result(x[0], fx[0], n, std(x),f_vec)

x0 = [0.3, 0.5, 0.3, 0.5]
f_vec = []
def append(xk):
    print(xk)
    f_vec.append(J(xk))
res = NelderMead(J,x0,eps=1e-2,nmax=50,disp=True,hist=True)
# res = Torczon(J,x0,eps=1e-2,disp=True,nmax=50,hist=True)
# res = minimize(J,x0,method="Nelder-Mead",options={"maxiter": 50},callback=append)
# res.f_vec = f_vec
print(res)
C, T = simu(*(res.x))
anim2d(C, T)
plt.plot(res.f_vec)
plt.show()