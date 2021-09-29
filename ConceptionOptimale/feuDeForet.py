#!python

import numpy as np
import matplotlib as mpl
from numpy.random import rand
import os
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
from mpl_toolkits.axes_grid1 import make_axes_locatable
from time import perf_counter

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

ns = 101
nt = 550
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
# plt.quiver(X, Y, V[0], V[1], units="xy")
# plt.show()
print(f"{dt = }")
T = np.zeros((nt, ns, ns))
C = np.zeros((nt, ns, ns))
C[0] = 5+randomC(3, -5, 5)
T[0] = (((X-0.1)**2+(Y-0.1)**2) <= 0.001)*5


def gradT(n):
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

def laplacienT(n):
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
    return int(T > 0.05)*(-60)

start = perf_counter()
for n in range(nt-1):
    progress(n, nt-1)
    T[n+1,1:-1,1:-1] = T[n,1:-1,1:-1]+dt*(mu*laplacienT(n)-np.einsum("ijk,ijk->jk",V[:,1:-1,1:-1],gradT(n))+R(T[n,1:-1,1:-1],C[n,1:-1,1:-1])*T[n,1:-1,1:-1])
    T[n+1, :, 0] = T[n+1, :, 1]
    T[n+1, :, -1] = T[n+1, :, -2]
    T[n+1, 0, :] = T[n+1, 1, :]
    T[n+1, -1, :] = T[n+1, -2, :]
    C[n+1, :, :] = C[n, :, :]+dt*R2(T[n])
print(f"Time taken: {perf_counter()-start:.2f} s")

fig, axs = plt.subplots(ncols=2)
norm = mpl.colors.Normalize(vmin=0, vmax=5)
norm2 = mpl.colors.Normalize(vmin=0,vmax=10)

im1 = axs[0].imshow(T[0], cmap='jet', norm=norm,extent=[0,1,1,0])
axs[0].invert_yaxis()
plt.colorbar(im1, pad=0.1, ax=axs[0])

im2 = axs[1].imshow(C[0],cmap='Greens',norm=norm2,extent=[0,1,1,0])
axs[1].invert_yaxis()
plt.colorbar(im2, pad=0.1,ax=axs[1])

def animate(i):
    Ti = T[i]
    Ci = C[i]
    im1.set_data(Ti)
    im2.set_data(Ci)
    axs[0].set_title(f"Frame {i}")

animation = FuncAnimation(fig, animate, frames=nt-1,interval=1)

plt.show()

print(np.max(T[-1]))
