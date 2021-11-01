#!python

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


def progress(i, n):
    i += 1
    k = int(i/n*20)
    print(f'\rProgression:[{k*"#"}{(20-k)*" "}] [{(i/n*100):.0f} %]',
          end='' if i != n else "\n", flush=True)


def randomC(n_circ, a, b):
    np.random.seed(0)
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
    C[0] = np.maximum(5+randomC(3, -5, 5)-10*firebreak, 0)
    T[0] = (((X-0.1)**2+(Y-0.1)**2) <= 0.0001)*10
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
        axs[0].set_title(f"Frame {i}")

    animation = FuncAnimation(fig, animate, frames=nt-1, interval=1)

    plt.show()


def cost_func(xmin, xmax, ymin, ymax):
    C, T = simu(xmin, xmax, ymin, ymax)
    fc = np.mean(C[-1])
    fc0 = np.mean(C[0])
    f = np.abs(fc0-fc)+(xmax-xmin)*(ymax-ymin)+100*max(0, 0.2-ymin)
    print(f, xmin, xmax, ymin, ymax)
    return f

def J(x): return cost_func(*x)

def NelderMead(f,dim,alpha=1,lb=0,ub=1,gamma=2,rho=1/2,sigma=1/2,eps=1e-8,nmax=1):
    x = (ub-lb)*rand(dim+1,dim)+lb
    n=0
    while n<=nmax:
        n+=1
        fx = f(x)
        sort = fx.argsort(axis=0)
        print(sort)
        x = x[sort]
        fx = fx[sort]
        xg = np.mean(x[:-1],axis=1)
        xr = xg+alpha*(xg-x[-1])
        fr = f(xr)
        if fx[0]<fr and fr<fx[-2]:
            x[-1]=xr
            continue
        elif fr<fx[0]:
            xe = xg+gamma*(xg-x[-1])
            if f(xe)<fr:
                x[-1]=xe
            else:
                x[-1]=xr
            continue
        elif fr>=fx[-2]:
            xc = xg+rho*(x[-1]-xg)
            if f(xc)<fx[-1]:
                x[-1]=xc
            continue
        for xi in x:
            xi = x[0]+sigma*(xi-x[0])
    return n,x[0] 




    


res = NelderMead(lambda x:x**2,dim=1)
print(res)
# x0 = [0.3, 0.5, 0.3, 0.5]
# res = minimize(J, x0, method="Nelder-Mead", tol=1e-3)
# print(res)
# C, T = simu(*(res.x))
# anim2d(C, T)
