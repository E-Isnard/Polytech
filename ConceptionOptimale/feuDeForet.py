import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

def progress(i, n):
    i += 1
    k = int(i/n*20)
    print(f'\rProgression:[{k*"#"}{(20-k)*" "}] [{(i/n*100):.0f} %]', end='' if i != n else "\n", flush=True)


ns = 100
nt=100
mu = 5e-3
delta = 1/ns
dt = delta**2/(8*mu)
x = np.linspace(0,1,ns)
y = np.linspace(0,1,ns)
X,Y = np.meshgrid(x,y)
u = np.zeros((2,ns,ns))
u=np.cos(np.pi*Y)
v=0.6*np.sin(np.pi/2*X)
V = np.array([u,v])


# plt.quiver(X,Y,u,v,units="xy")
# plt.show()

# dt=0.1

T = np.zeros((nt,ns,ns))
T[0,10:15,10:15] = 100

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

plt.contourf(X,Y,T[-1])
plt.show()
# vmin=np.min(T)
# vmax=np.max(T)
# colormap = "magma"
# fig = plt.figure()
# ax = plt.axes()
# plot = ax.contourf(T[0],vmin=vmin,vmax=vmax,cmap=colormap)



# def animate(n):
#     ax.clear()
#     plot = ax.contourf(T[n],vmin=vmin,vmax=vmax,cmap=colormap)
#     plt.title(f"{n*dt:.2f}")
#     return plot,

# anim = FuncAnimation(fig,animate,frames=nt-1)
# plt.show()

