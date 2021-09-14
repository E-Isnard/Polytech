import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
from mpl_toolkits.axes_grid1 import make_axes_locatable

def progress(i, n):
    i += 1
    k = int(i/n*20)
    print(f'\rProgression:[{k*"#"}{(20-k)*" "}] [{(i/n*100):.0f} %]', end='' if i != n else "\n", flush=True)


ns = 100
nt=100
mu = 5e-3
delta = 1/ns
x = np.linspace(0,1,ns)
y = np.linspace(0,1,ns)
X,Y = np.meshgrid(x,y)
u = np.zeros((2,ns,ns))
u=np.cos(np.pi*Y*0)
v=0.6*np.sin(np.pi/2*X*0)
V = np.array([u,v])
c = np.max(u**2+v**2)
dt = min(delta**2/(8*mu),0.125*c/delta)

plt.quiver(X,Y,u,v,units="xy")
plt.show()

T = np.zeros((nt,ns,ns))
T[0,10:30,10:30] = 100

def gradT(n,i,j):
    grad = np.zeros((2,1))
    if u[i,j]<=0:
        grad[0]=(T[n,i+1,j]-T[n,i,j])/delta
    else:
        grad[0]=(T[n,i,j]-T[n,i-1,j])/delta

    if v[i,j]<=0:
        grad[1]=(T[n,i,j+1]-T[n,i,j])/delta
    else:
        grad[1]=(T[n,i,j]-T[n,i,j-1])/delta


    return grad

def laplacienT(n,i,j):
    return (T[n,i+1,j]+T[n,i-1,j]+T[n,i,j+1]+T[n,i,j-1]-4*T[n,i,j])/delta**2


for n in range(nt-1):
    progress(n,nt-1)
    for i in range(1,ns-1):
        for j in range(1,ns-1):
            grad = gradT(n,i,j)
            T[n+1,i,j]=T[n,i,j]+dt*(mu*laplacienT(n,i,j)+u[i,j]*grad[0]+v[i,j]*grad[1])

fig = plt.figure()
ax = fig.add_subplot(111)
div = make_axes_locatable(ax)
cax = div.append_axes('right', '5%', '5%')
cv0 = T[0]
im = ax.imshow(cv0,origin="lower")
cb = fig.colorbar(im,cax=cax)
tx = ax.set_title('Frame 0')
def animate(i):
    arr = T[i]
    vmax = 100
    vmin     = 0
    im.set_data(arr)
    im.set_clim(vmin, vmax)
    tx.set_text('Frame {0}'.format(i))
    # In this version you don't have to do anything to the colorbar,
    # it updates itself when the mappable it watches (im) changes

ani = FuncAnimation(fig, animate, frames=nt-1)


plt.show() 