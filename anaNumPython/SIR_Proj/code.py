import math
import numpy as np
import matplotlib.pyplot as plt
from LU import *
from numpy.linalg import norm, solve, det, inv
from scipy.optimize import root, newton
from time import time



# alpha = 0.25
# beta = 2
# delta = 0.8
# eta = 0.95
# gamma = 0.8
N = 67063703


# R0 = beta/(alpha+gamma)+(alpha/alpha+gamma)*(delta*beta/eta)


def newtonNd(g, J, u0, eps=1E-3, nmax=10):
    """
    Algorithme de Newton pour résoudre g(x)=0
    J est une fonction donnant la jacobienne de g
    """
    u = u0
    err = norm(g(u))
    n = 0
    while err > eps and n < nmax:
        L, U, P = factLUP(J(u))
        u = u.reshape((4, 1))+solveLinSysP(L, U, P, -g(u))
        err = norm(g(u))
        n += 1

    return u


def pointFixe(g, x0, eps=-1E-10, nmax=10):
    """
    Algorithme du point fixe pour résoudre g(x)=x
    
    g doit être contractante pour que la méthode converge
    """
    k = 0
    x = x0.reshape((4, 1))
    fx = g(x0)
    err = norm(fx-x)/norm(x)
    while k <= nmax and err > eps:
        k += 1
        x = fx
        fx = g(x)
        err = norm(fx-x)/norm(x)

    return x

def eulerImplPtFixe(f,x0, t0, tf, n):
    """
    Méthode d'Euler Implcicite pour résoudre x'=f(x) utilisant la méthode du point fixe
    
    x0: condition initiale

    t0: temps inital

    tf: temps final

    n: nombre de pas de temps
    """
    t = np.linspace(t0, tf, n)
    h = (tf - t0) / n
    p = len(x0)
    x = np.zeros((n, p))

    x[0, :] = x0

    for i in range(n - 1):

        def g(u): return x[i, :].reshape((4, 1)) + h * f(u)
        x[i + 1, :] = pointFixe(g, x[i, :]).reshape((4,))

    return t, x

def f2(x, alpha, beta, delta, gamma, eta,mu1,mu2): #Avec la mortalité 
    """
    x'=f(x)

    x=(x1,x2,x3,x4,x5) ou x1=S,x2=I,x3=T ,x4=R et x5=M

    y=x'
    """
    y = np.zeros((5, 1))

    y[0] = -beta / N * (x[1] + delta * x[2]) * x[0]
    y[1] = beta / N * (x[1] + delta * x[2]) * x[0] - (alpha + gamma+mu1) * x[1]
    y[2] = alpha * x[1] - eta * x[2] - mu2*x[2]
    y[3] = gamma * x[1] + eta * x[2]
    y[4] = mu1*x[1]+mu2*x[2]

    return y

def f(x,alpha,beta,delta,gamma,eta):
    """
    x'=f(x)

    x=(x1,x2,x3,x4) ou x1=S,x2=I,x3=T et x4=R

    y=x'
    """
    y = np.zeros((4, 1))

    y[0] = -beta / N * (x[1] + delta * x[2]) * x[0]
    y[1] = beta / N * (x[1] + delta * x[2]) * x[0] - (alpha + gamma) * x[1]
    y[2] = alpha * x[1] - eta * x[2]
    y[3] = gamma * x[1] + eta * x[2]

    return y


x0 = np.array([1, 2, 3, 4])


def JacF(x,alpha,beta,delta,gamma,eta):
    """
    Donne la jacobienne de f
    """
    J = np.zeros((4, 4))

    J[0, 0] = -beta / N * (x[1] + delta * x[2])
    J[0, 1] = -beta / N * x[0]
    J[0, 2] = -beta / N * delta * x[0]
    J[0, 3] = 0

    J[1, 0] = beta / N * (x[1] + delta * x[2])
    J[1, 1] = beta / N * x[0] - (alpha + gamma)
    J[1, 2] = beta / N * delta * x[0]
    J[1, 3] = 0

    J[2, 0] = 0
    J[2, 1] = alpha
    J[2, 2] = -eta
    J[2, 3] = 0

    J[3, 0] = 0
    J[3, 1] = gamma
    J[3, 2] = eta
    J[3, 3] = 0

    return J


def eulerImplNewton(f,JacF,x0, t0, tf, n):
    """
    Méthode d'Euler implicite utilisant l'agorithme de Newton
    
    x0: condition initiale

    t0: temps inital

    tf: temps final

    n: nombre de pas de temps
    
    """
    t = np.linspace(t0, tf, n)
    h = (tf - t0) / n
    p = len(x0)
    x = np.zeros((n, p))

    x[0, :] = x0

    def JacG(u): return h * JacF(u) - np.eye(4, 4)

    for i in range(n - 1):

        def g(u): return x[i, :].reshape((4, 1)) - \
            u.reshape((4, 1)) + h * f(x[i, :])
        x[i + 1, :] = newtonNd(g, JacG, x[i, :]).reshape((4,))

    return t, x


def RK4(f,x0, t0, tf, n):
    """
    Résolution de x'=f(x) avec la methode de runge-kutta 4
    
    x0: condition initiale

    t0: temps inital

    tf: temps final

    n: nombre de pas de temps
    """
    t = np.linspace(t0, tf, n)
    h = (tf - t0)/n
    p = len(x0)
    x = np.zeros((n, p))
    x[0, :] = x0
    for i in range(n - 1):
        y = x[i, :].reshape((4, 1))
        k1 = f(y)
        k2 = f(y + h*(k1/2))
        k3 = f(y+h*k2/2)
        k4 = f(y+h*k3)
        x[i+1, :] = (y+(h/6)*(k1+2*k2+2*k3+k4)).reshape((4,))
    return t, x


def eulerExpl(f,x0, t0, tf, n):
    """

    Resolution de x'=f(x) avec la methode d'Euler explicite
    
    x0: condition initiale

    t0: temps inital

    tf: temps final

    n: nombre de pas de temps
    """
    t = np.linspace(t0, tf, n)
    h = (tf - t0) / n
    p = len(x0)
    x = np.zeros((n, p))
    x[0, :] = x0

    for i in range(n - 1):
        x[i+1, :] = x[i, :]+h*f(x[i, :]).reshape((p,))

    return t, x
        

def SIRModel2(t0,tf,n,alpha, beta, delta, gamma, eta,mu1,mu2):
    """
    Affiche le modèle avec la mortalité en plus en utilisant euler explicite
    """
    F = lambda x: f2(x, alpha, beta, delta, gamma, eta,mu1,mu2)
    x0 = np.array([N-1000,1000,0,0,0])
    t, x = eulerExpl(F, x0, t0, tf, n)

    plt.plot(t, x / N)
    plt.title("Graphe avec $\\alpha$={}, $\\beta$={}, $\gamma$={}, $\delta$={} ,$\eta$={},$\mu_1$={} et $\mu_2$={}".format(
        alpha, beta, gamma, delta, eta,mu1,mu2))
    plt.legend(["Sains", "Infectes", "Traites", "Retablis","Morts"])
    plt.show()
    
def SIRModel(mthd,t0,tf,n,alpha, beta, delta, gamma, eta):
    """
    Fonction affichant le graphe du modèle
    
    mthd=1: EulerExpl
    mthd=2: EulerImplPtFixe
    mthd=3: EulerImplNewton
    mthd=4: Runge-Kutta 4
    """
    print("R0=",beta/(alpha+gamma)+(alpha/alpha+gamma)*(delta*beta/eta))
    F = lambda x: f(x, alpha, beta, delta, gamma, eta)
    JacF1var = lambda x:JacF(x,alpha,beta,delta,gamma,eta)
    x0 = np.array([N-1000,1000,0,0])
    start = time()
    if mthd==1:
        t, x = eulerExpl(F, x0, t0, tf, n)
    elif mthd==2:
        t, x = eulerImplPtFixe(F, x0, t0, tf, n)
    elif mthd==3:
        t,x = eulerImplNewton(F,JacF1var,x0,t0,tf,n)
    else:
        t, x = RK4(F, x0, t0, tf, n)
        
    tempsExec = time()-start
    print("Temps d'éxécution:",tempsExec)

    plt.plot(t, x / N)
    plt.title("Graphe avec $\\alpha$={}, $\\beta$={}, $\gamma$={}, $\delta$={} et $\eta$={}".format(
    alpha, beta, gamma, delta, eta))
    plt.legend(["Sains", "Infectes", "Traites", "Retablis"])
    plt.show()



    

