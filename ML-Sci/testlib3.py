
"""Backend supported: tensorflow.compat.v1, tensorflow, pytorch

Documentation: https://deepxde.readthedocs.io/en/latest/demos/poisson.1d.dirichlet.html
"""
import os
os.environ["DDEBACKEND"] = "pytorch"
import deepxde as dde
import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
# Import tf if using backend tensorflow.compat.v1 or tensorflow
from deepxde.backend.pytorch import torch
from scipy.interpolate import griddata
# Import torch if using backend pytorch
# import torch


def pde(x,u):
	u_t = dde.grad.jacobian(u,x,i=0,j=1)
	u_x = dde.grad.jacobian(u,x,i=0,j=0)
	u_xx = dde.grad.hessian(u,x,i=0,j=0)
	return u_t+u*u_x-0.01*np.pi*u_xx

geom = dde.geometry.Interval(-1,1)
time_domain = dde.geometry.TimeDomain(0,0.99)
geom_time = dde.geometry.GeometryXTime(geom,time_domain)
def boundary(x,on_boundary):
	return on_boundary

def g(x):
	return 0
def h(x):
	return -np.sin(np.pi*x[:,0:1])

bc = dde.DirichletBC(geom_time,g,boundary)
ic = dde.IC(geom_time,h,boundary)
data = dde.data.TimePDE(geom_time,pde,[bc,ic],num_domain=2540,num_boundary=80,num_initial=160)

layer = [2]+[50]*4+[1]
nn = dde.maps.FNN(layer,"tanh","Glorot normal")

model = dde.Model(data,nn)
model.compile("adam",lr=1e-3)
losshistory, train_state = model.train(epochs=10000)

G = geom_time.uniform_points(100000)
print(G.shape)
x = np.linspace(-1,1)
G = np.ones((len(x),2))
G[:,0] = x
u = model.predict(G)

# plt.scatter(G[:,1],G[:,0],c=u)
# plt.show()
ut = model.predict(G)
plt.plot(x,ut)
# plt.plot(x,-np.sin(np.pi*x))
plt.show()



