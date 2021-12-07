import numpy as np
from numpy.random import multivariate_normal
import matplotlib.pyplot as plt
from scipy.linalg import sqrtm
pi = np.pi
@np.vectorize
def kg(x,x2,sigma=1,theta=1):
    return sigma**2*np.exp(-np.linalg.norm(x-x2)**2/theta**2)
@np.vectorize
def ke(x,x2,sigma=1,theta=1):
    return sigma**2*np.exp(-np.linalg.norm(x-x2)/theta)

@np.vectorize
def kb(x,x2):
    return min(x,x2)


x = np.linspace(-1,1,num=1000)

# plt.plot(x,kg(x,0,theta=0.2),label="$k_G(x,0)$")
# plt.plot(x,ke(x,0,theta=0.2),label="$k_E(x,0)$")
# plt.plot(x,kb(x,0),label="$k_b(x,0)$")
# plt.legend()
# plt.title("Plot de différentes fonctions de covariance")
# plt.xlabel("x")
# plt.ylabel("y")
# plt.show()
n=100
theta = 1
sigma = 1
x = np.linspace(0,1,num=n)
Kg = np.zeros((n,n))
Ke = np.zeros((n,n))
Kb = np.zeros((n,n))
for i,xi in enumerate(x):
    for j,xj in enumerate(x):
        Kg[i,j] = kg(xi,xj,theta=theta,sigma=sigma)
        Ke[i,j] = ke(xi,xj,theta=theta,sigma=sigma)
        Kb[i,j] = kb(xi,xj)
Z = multivariate_normal(np.zeros((n,)),np.eye(n,n),size=1).T
Yg = np.real(sqrtm(Kg))@Z
Ye = np.real(sqrtm(Ke))@Z
Yb = np.real(sqrtm(Kb))@Z

# plt.plot(x,Yg,label="$k_G$")
# plt.plot(x,Ye,label="$k_E$")
# plt.legend()
# plt.show()

def fb(x1,x2):
    (x2-5.1/(4*pi**2)*x1**2+5/pi*x1-6)**2+10*((1-1/(8*pi))*np.cos(x1)+1)+5*x1

x1 = np.linspace(-5,10)
x2 = np.linspace(0,15)
X1,X2 = np.meshgrid(x1,x2)
Z = np.zeros(X1.shape)
for i,xi in enumerate(x1):
    for j,xj in enumerate(x2):
        Z[i,j]=fb(xi,xj)
print(Z)
plt.contourf(X1,X2,Z)
plt.show()