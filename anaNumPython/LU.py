import numpy as np
import numpy.linalg as lin


def factLU(A):
    """
    Factorise une matrice A en la forme A=LU ou L est triangulaire inférieure et U triangulaire supérieure
    """
    n = A.shape[1]
    U = A.copy()
    L = np.eye(n)

    for k in range(0, n-1):
        for i in range(k + 1, n):
            alpha = U[i, k] / U[k, k]
            U[i, :] = U[i, :] - alpha * U[k, :]
            L[i, k] = alpha

    return L, U


def descente(L, b):
    """
    Résout le système Lx=b où L est triangulaire inférieure
    """
    n = b.size

    x = np.zeros((n, 1))
    x[0] = b[0] / L[0, 0]
    for j in range(1, n):
        S = 0
        for i in range(0, j):
            S += x[i]*L[j, i]
        x[j] = (b[j] - S)/(L[j, j])

    return x


def remontee(U, b):
    """
     Résout le système Ux=b où U est triangulaire supérieure
    """
    n = b.size
    x = np.zeros((n, 1))
    x[n - 1] = x[n - 1] / U[n - 1, n - 1]
    for j in range(0, n):
        j = n-j-1
        S = 0
        for i in range(j, n):
            S += x[i] * U[j, i]
        x[j] = (b[j]-S)/(U[j, j])

    return x


def solveLinSys(L, U, b):
    """
    Résout le systeme LUx=b où L est triangulaire inférieure et U triangulaire supérieure
    """
    y = descente(L, b)
    x = remontee(U, y)
    return x

def solveLinSysP(L, U, P, b):
    """
    Résout le systeme LUx=Pb où L est triangulaire inférieure et U triangulaire supérieure
    """
    
    return solveLinSys(L,U,np.dot(P,b))


def factLUP(A):
    """
    Factorise une matrice A en la forme PA=LU ou L est triangulaire inférieure ,U triangulaire supérieure et P une matrice de permutation
    """
    U = np.copy(A)
    N = U.shape
    P = np.eye(N[0])
    L = np.eye(N[0])
    for k in range(0, N[0]-1):
        #calcul de l'indice du plus grand pivot
        ind = np.argmax(abs(U[k:N[0], k]))

        ind = ind+k
        #calcul de la matrice de permutation utilisée à l'étape k et échange des lignes k et ind dans la matrice U
        Ptmp = np.eye(N[0])
        if(ind != k):
            Ptmp[ind, k] = 1.
            Ptmp[ind, ind] = 0.
            Ptmp[k, ind] = 1.
            Ptmp[k, k] = 0.
            # Mise à jour de la matrice de permutation globale
            P = np.dot(Ptmp, P)
            u = np.copy(U[ind, k:N[0]])
            U[ind, k:N[0]] = U[k, k:N[0]]
            U[k, k:N[0]] = u
        #Combinaison linéaire pour mettre des O sur la colonne k et construction de la matrice M (équivalent de la matrice L mais pour l'étape k uniquement)
        M = np.eye(N[0])
        for j in range(k+1, N[0]):
            alpha = U[j, k]/U[k, k]
            U[j, :] = U[j, :]-alpha*U[k, :]
            M[j, k] = alpha
        #Mise à jour de la matrice L
        L = np.dot(L, np.dot(np.transpose(Ptmp), M))
    # Permutation de la matrice L
    L = np.dot(P, L)

    return L, U, P


# A = np.matrix("[2 0 4 -3;-2 1 -1 1;-4 -1 -12 9;-2 1 1 -4]")
# B = np.matrix("[1 1 7 1; 3 1 -1 -3;1 1 -5 1; 1 2 3 4]")
# L, U, P = factLUP(A)
# print(L)
# print(U)
# print(P)
# print(L * U)
# print(P * A)
# print(P)
# b = np.ones((4, 1))
# x = solveLinSysP(L, U, P, b)
# print(x)
# print(lin.inv(A)*b)

# A = np.array([[1, 1 + 0.5E-15, 3], [2, 2, 20], [3, 6, 4]])
# L, U = factLU(A)
# print(np.dot(L, U))

# L2, U2, P = factLUP(A)
# print(np.dot(P,np.dot(L2,U2)))


# ϵ = 1E-13
# b = np.zeros((2, 1))
# b[0] = 1 + ϵ
# b[1]=2
# A = np.array([[ϵ,1],[1,1]])

# L, U = factLU(A)

# x = solveLinSys(L, U, b)
# print(x)

# L2, U2, P = factLUP(A)
# x2 = solveLinSysP(L2, U2, P, b)
# print(P)
# print(x2)

# e = 1E-14
# A = np.array([[e, 1, 1], [1, 1, -1], [1, 1, 2]])
# b = np.array([[1,-1,1]]).T

# L, U = factLU(A)
# x = solveLinSys(L, U, b)
# print(x,end="\n")
# print(np.dot(lin.inv(A),b))
# L2, U2, P = factLUP(A)
# x2 = solveLinSysP(L, U, P, b)
# print(x2)
