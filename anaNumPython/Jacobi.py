import numpy as np
from numpy.linalg import norm, inv,solve,eigvals
from LU import *
import matplotlib.pyplot as plt

def jacobi(A, b, x0, ϵ, Nmax):
    
    n = A.shape[0]
    x = x0
    k = 0
    diag = np.diag(A)
    invD = np.diag(np.reciprocal(diag))
    E = -np.tril(A, -1)
    F = -np.triu(A, 1)
    N=E+F

    
    
   
    while norm(np.dot(A,x)-b)/norm(b)>ϵ and k<Nmax:
        k += 1
        x= np.dot(invD,np.dot(N,x)+b)

    
    return x


def gaussSeidel(A, b, x0, ϵ, Nmax):
    n = A.shape[0]
    x = x0
    k = 0
    D = np.diag(np.diag(A))
    L = np.tril(A, k=-1)
    U = np.triu(A, k=1)
    
    M = L + D
    
    
    while norm(np.dot(A,x)-b)/norm(b)>ϵ and k<Nmax:
        k += 1
        x = descente(M, b - np.dot(U, x))
    
    return x


def SOR(A, b, x0, ϵ, ω, Nmax):
    
    n = A.shape[0]
    x = x0
    k = 0
    D = np.diag(np.diag(A))
    L = np.tril(A, k=-1)
    U = np.triu(A, k=1)
    

    M = ω*L + D
    
    while norm(np.dot(A, x)-b)/norm(b) > ϵ and k < Nmax:
        k += 1
        x = descente(M, ω * b - np.dot(ω * U - (1 - ω) * D, x))
        B = np.dot(inv((M)), -U-(1-ω)*D)
        ρ = max(np.abs(eigvals(inv(B))))

    return (x, k, ρ)



# for n in range(2,40):
    

#     vect2 = 2 * np.ones((n,))
#     vectm1 = -np.ones((n-1,))
#     A = np.diag(vect2) + np.diag(vectm1, k=1) + np.diag(vectm1, k=-1)
#     b = np.ones((n,1))
#     x0 = np.ones((n, 1))

#     x1 = jacobi(A, b, x0, 1E-6, 100)

#     L, U = factLU(A)
#     x2 = solveLinSys(L, U, b)
#     ev.append(norm(x2-x1))

# plt.plot(list(range(2, 40)), ev)
# plt.show()

# ev = []
# nv=list(range(2,40))
# for n in nv:
    

#     vect2 = 2 * np.ones((n,))
#     vectm1 = -np.ones((n-1,))
#     A = np.diag(vect2) + np.diag(vectm1, k=1) + np.diag(vectm1, k=-1)
#     b = np.ones((n,1))
#     x0 = np.zeros((n, 1))


#     x1 = gaussSeidel(A, b, x0, 1E-5, 100)
#     ev.append(norm(x1-solve(A,b)))

# plt.plot(nv, ev)
# plt.show()
n=3
vect2 = 2 * np.ones((n,))
vectm1 = -np.ones((n-1,))
A = np.diag(vect2) + np.diag(vectm1, k=1) + np.diag(vectm1, k=-1)
b = np.ones((n,1))
x0 = np.zeros((n, 1))

ωv = np.linspace(0.001, 1.9999,100)
kv = []
ρv = []
for ω in ωv:
    
    _, k, ρ = SOR(A, b, x0, 1E-6, ω, 300)
    kv.append(k)
    ρv.append(ρ)

print(kv)

# plt.plot(ωv, ρv)
# print(ρv)
# plt.show()

ω0 = ωv[np.argmin(kv)]
E = -np.tril(A, -1)
F = -np.triu(A, 1)
N = E+F
B = np.dot(inv(np.diag(np.diag(A))), N)
ρB = max(abs(eigvals(B)))
ω1 = 2/(1+np.sqrt(1-ρB**2))


print(ω0)
print(ω1)

