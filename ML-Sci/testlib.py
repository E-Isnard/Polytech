"""Backend supported: tensorflow.compat.v1, tensorflow, pytorch

Documentation: https://deepxde.readthedocs.io/en/latest/demos/poisson.1d.dirichlet.html
"""
import os
os.environ["DDEBACKEND"] = "pytorch"
import deepxde as dde
import matplotlib.pyplot as plt
import numpy as np
# Import tf if using backend tensorflow.compat.v1 or tensorflow
from deepxde.backend.pytorch import torch
# Import torch if using backend pytorch
# import torch


def pde(x,u):
	u_xx = dde.grad.hessian(u,x)
	return -u_xx - torch.sin(np.pi*x)

geom = dde.geometry.Interval(-1,1)

def boundary(x,on_boundary):
	return on_boundary

f = lambda x: 1/np.pi**2*np.sin(np.pi*x)
def g(x):
	return 0

bc = dde.DirichletBC(geom,g,boundary)
data = dde.data.PDE(geom,pde,bc,16,2,num_test=100 )

layer = [1]+[50]*3+[1]
nn = dde.maps.FNN(layer,"tanh","Glorot uniform")

model = dde.Model(data,nn)
model.compile("adam",lr=1e-3)
model.train(epochs=10000)

x = geom.uniform_points(1000,True)
y = model.predict(x)
plt.plot(x,y,label="Solution numérique")
plt.plot(x, f(x),label="Solution exacte")
plt.title("Résultat de DeepXDE pour résoudre une équation de Poisson 1D")
plt.legend()
plt.show()



