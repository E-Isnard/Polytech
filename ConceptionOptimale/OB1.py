import enum
import numpy as np
from numpy.random import multivariate_normal
import matplotlib.pyplot as plt
from scipy.linalg import sqrtm
import matplotlib.cm as cm
pi = np.pi
# @np.vectorize
def kg(x,x2,sigma=1,theta=0.2):
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

def Rn(Xn,k=kg):
    n = len(Xn)
    Kn = np.zeros((n,n))
    for i,xi in enumerate(Xn):
        for j,xj in enumerate(Xn):
            Kn[i,j] = k(xi,xj)
    return Kn

def fb(x1,x2):
    return (x2-5.1/(4*pi**2)*x1**2+5/pi*x1-6)**2+10*((1-1/(8*pi))*np.cos(x1)+1)+5*x1

N=51
x1 = np.linspace(-5,10,N)
x2 = np.linspace(0,15,N)
X1,X2 = np.meshgrid(x1,x2)
Z = np.zeros(X1.shape)

# for i,xi in enumerate(x1):
#     for j,xj in enumerate(x2):
#         Z[i,j]=fb(xi,xj)
# plt.imshow(Z)
# plt.show()
# fig = plt.figure()
# ax = plt.axes(projection="3d")
# ax.plot_surface(X1,X2,Z,cmap=cm.jet)
# plt.show()

x = np.linspace(0,1,num=3)
X = np.array([[xi,xj] for xi in x for xj in x])

def y(X):
    y = np.zeros((len(X),1))
    for i,x in enumerate(X):
        y[i] = fb(*x)
    return y


def sigman(Rn,yn,eps=1e-5):
    n=len(yn)
    u = np.linalg.solve(Rn+eps*np.eye(n,n),yn)
    return np.dot(yn.T,u)/n

def Kn(X):
    yn = y(X)
    sn = sigman(Rn,yn)
    return sn**2*Rn

def kn(x,Xn):
    R = Rn(Xn)
    yn = y(X)
    sn = sigman(R,yn)
    k = np.zeros((len(x),1))
    for i in range(len(x)):
        k[i] = kg(x,xi,sigma=sn)
    return k
        






    


