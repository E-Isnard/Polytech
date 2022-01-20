import enum
import numpy as np
from numpy.random import multivariate_normal
import matplotlib.pyplot as plt
from scipy.linalg import sqrtm
from scipy.optimize import minimize
import matplotlib.cm as cm
from pyDOE import lhs
from pyKriging import kriging
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

def Rn(Xn,k_func=kg):
    n = len(Xn)
    Kn = np.zeros((n,n))
    for i,xi in enumerate(Xn):
        for j,xj in enumerate(Xn):
            Kn[i,j] = k_func(xi,xj)
    return Kn

def fb(x1,x2):
    return (x2-5.1/(4*pi**2)*x1**2+5/pi*x1-6)**2+10*((1-1/(8*pi))*np.cos(x1)+1)+5*x1

N=51
x1 = np.linspace(-5,10,N)
x2 = np.linspace(0,15,N)

def eval_func(x1,x2,f):
    X1,X2 = np.meshgrid(x1,x2)
    Z = np.zeros(X1.shape)
    for i,xi in enumerate(x1):
        for j,xj in enumerate(x2):
            Z[i,j]=f([xi,xj])
    return X1,X2,Z
# X1,X2,Z = eval_func(x1,x2,fb)
# plt.imshow(Z)
# plt.show()
# fig = plt.figure()
# ax = plt.axes(projection="3d")
# ax.plot_surface(X1,X2,Z,cmap=cm.jet)
# plt.show()

x_uni = np.linspace(0,1,num=3)
X_uni = np.array([[xi,xj] for xi in x_uni for xj in x_uni])

def r(x,X,k_func=kg):
    r_vec = np.zeros((len(X),1))
    for i,xi in enumerate(X):
        r_vec[i] = k_func(x,xi)
    return r_vec

def R(X,k_func=kg):
    R_mat = np.zeros((len(X),len(X)))
    for i,xi in enumerate(X):
        for j,xj in enumerate(X):
            R_mat[i,j] = k_func(xi,xj)
    return R_mat

def y_func(X):
    y = np.zeros((len(X),1))
    for i,xi in enumerate(X):
        y[i] = fb(*xi)
    return y


def mn(x,X,k_func=kg):
    r_vec = r(x,X,k_func)
    R_mat = R(X,k_func)
    y = y_func(X)
    return r_vec.T@(np.linalg.solve(R_mat,y))

def sigman(Xn,k_func=kg):
    y = y_func(Xn)
    R_mat = R(Xn,k_func)
    n = R_mat.shape[0]
    return 1/n*y.T@(np.linalg.solve(R_mat,y))
x_test = np.linspace(0,1)
X_lhs = lhs(2,9)
X1,X2,Z = eval_func(x_test,x_test,lambda x: fb(*x))
# plt.imshow(Z)
# plt.colorbar()
# plt.show()
# X1,X2,Z = eval_func(x_test,x_test,lambda x: mn(x,X_uni))
# plt.imshow(Z)
# plt.colorbar()
# plt.show()
X1,X2,z = eval_func(x_test,x_test,lambda x: mn(x,X_lhs))
# plt.imshow(Z)
# plt.colorbar()
# plt.show()
# data = np.append(X_lhs,y_func(X_lhs),axis=1)
# ok = OrdinaryKriging(data[:,0],data[:,1],data[:,2])
# z,ss = ok.execute("grid",x_test,x_test)
fig,(ax1,ax2) = plt.subplots(1,2)
plot = ax1.imshow(z)
ax1.set_title("Ordinary Kriging")
ax2.imshow(Z)
ax2.set_title("True Function")
fig.colorbar(plot,ax = (ax1,ax2))
plt.show()
# print(mn(X_uni[0],X_uni))
# y = y_func(X_uni)
# print(y[0])


# print(mn(X_lhs[0],X_lhs))
# y = y_func(X_lhs)
# print(y[0])
@np.vectorize
def ll(theta,X=X_uni):
    k_func = lambda x,x2: kg(x,x2,theta=theta)
    sigma2 = sigman(X,k_func)
    R_mat = R(X,k_func)
    n = R_mat.shape[0]
    return -n/2*np.log(2*np.pi)-n/2*np.log(sigma2)-1/2*np.log(np.linalg.det(R_mat))-n/2

theta_vec = np.linspace(0.1,25)
ll_vec = ll(theta_vec)

# plt.plot(theta_vec,ll_vec)
# plt.show()

theta_opt = minimize(lambda x: -ll(x),0.2)
print(theta_opt.x)
    


