import numpy as np
from numpy.linalg import norm, solve, det, inv
import copy
from LU import *


S1 = [-11716.227778, -10118.754628, 21741.083973]
S2 = [-12082.643974, -20428.242179, 11741.374154]
S3 = [14373.286650, -10448.439349, 19596.404858]
d = [22163.847742, 21492.777482, 21492.469326]


def newtonNd(f, J, x0, eps, nmax=1000):
    x = x0
    n = 1
    erreur = 1.0
    while n <= nmax and erreur > eps:
        A = J(x)
        L,U,P = factLUP(A)
        xold = copy.deepcopy(x)
        # x = x-np.dot(inv(J(x)), f(x))
        x = solveLinSysP(L,U,P,-f(x)+np.dot(A,x))
        n += 1
        erreur = norm(x-xold)/norm(x)

    return x


def f(x):
    y = np.zeros((3, 1))
    y[0] = (x[0]-S1[0])**2+(x[1]-S1[1])**2+(x[2]-S1[2])**2-d[0]**2
    y[1] = (x[0]-S2[0])**2+(x[1]-S2[1])**2+(x[2]-S2[2])**2-d[1]**2
    y[2] = (x[0]-S3[0])**2+(x[1]-S3[1])**2+(x[2]-S3[2])**2-d[2]**2
    return y


def Jac(x):
    J = np.zeros((3, 3))
    for i in range(0, 3):
        J[0, i] = 2.0*x[i]-2.0*S1[i]

    for i in range(0, 3):
        J[1, i] = 2.0*x[i]-2.0*S2[i]

    for i in range(0, 3):
        J[2, i] = 2.0*x[i]-2.0*S3[i]

    return J


x0 = np.zeros((3, 1))
xR = newtonNd(f, Jac, x0, 1E-3)
print("xR=",xR.T,end="\n\n")
print("||xR|| =",norm(xR),"km et le rayon de la terre est RT=6371km donc le résultat est cohérent")
