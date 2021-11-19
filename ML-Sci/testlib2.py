
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
	u_xx = dde.grad.hessian(u,x,i=0,j=0)
	u_yy = dde.grad.hessian(u,x,i=1,j=1)
	return -u_xx -u_yy - 1

# geom = dde.geometry.Polygon([[0, 0], [1, 0], [1, -1], [-1, -1], [-1, 1], [0, 1]])
geom = dde.geometry.Disk(0,1)
def boundary(x,on_boundary):
	return on_boundary

def g(x):
	return 0

bc = dde.NeumannBC(geom,g,boundary)
data = dde.data.PDE(geom,pde,bc,1200,120,num_test=0)

layer = [2]+[50]*4+[1]
nn = dde.maps.FNN(layer,"tanh","He normal")

model = dde.Model(data,nn)
model.compile("adam",lr=1e-3)
losshistory, train_state = model.train(epochs=10000)

G = geom.uniform_points(100000)
u = model.predict(G)
x = G[:,0]
y = G[:,1]

plt.scatter(x,y,c=u)
plt.title("Solution de DeepXDE pour résoudre $-\Delta u = 1$")
plt.xlabel("x")
plt.ylabel("y")
plt.colorbar()
plt.show()
