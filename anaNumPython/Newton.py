import math
import numpy as np
import matplotlib.pyplot as plt

def sqrtNewton(a,nmax,eps,x0):
    x=x0
    n=1
    while n<=nmax and abs(x*x-a)>eps :
        x = x - (x*x-a)/(2*x)
        n += 1
    return x,n

def sqrtPtFixe(a,nmax,eps,x0):
    x = x0
    n = 1
    while n<=nmax and abs(x*x-a)>eps:
        x = (2 * x + 2 / x) / 3
        n+=1
    return x,n


epsv = np.logspace(start=-13, stop=-1, num=50)
nvNewton = []
nvPtFixe = []

for eps in epsv:
    _, nNewton = sqrtNewton(2, 10000, eps, 1)
    _, nPtFixe = sqrtPtFixe(2, 10000, eps, 1)
    
    nvNewton.append(nNewton)
    nvPtFixe.append(nPtFixe)

plt.plot(epsv, nvNewton,label="Newton")
plt.plot(epsv, nvPtFixe, label="PtFixe")
plt.legend()

plt.show()

#Newton est bien plus efficace que la méthode du point fixe

