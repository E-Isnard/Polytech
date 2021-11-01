#!python
import numpy as np
import matplotlib.pyplot as plt


def prod_vect(u,v):
    return np.array([u[1]*v[2]-u[2]*v[1],u[2]*v[0]-u[0]*v[2],u[0]*v[1]-u[1]*v[0]])

def area_tri(a,b,c):
    u = b-a
    v = c-a
    return np.linalg.norm(prod_vect(u, v))/2


def isInTriangle(a,b,c,x):
    A1 = area_tri(a, b, x)
    A2 = area_tri(a, x, c)
    A3 = area_tri(x, b, c)
    A = area_tri(a, b, c)
    return (A==(A1+A2+A3) and A!=0)

def d(x,y):
    return np.linalg.norm(x-y)


if __name__=="__main__":
    u = np.array([1,2,3])
    v=np.array([4,5,6])

    # print(prod_vect(u, v))

    a = np.array([0,0,0])
    b = np.array([0,1,0])
    c = np.array([1,0,0])
    x = np.array([1/2,1/2,0])
    # print(area_tri(a, b, c))
    # print(isInTriangle(a, b, c, x))

    data = np.genfromtxt("test_temperature_triangle.csv",delimiter=";",dtype=float)
    C = data[:-1,:]
    q = data[-1,:].reshape(-1,1)

    D = np.zeros((2,C.shape[1]))
    for i,pi in enumerate(C.T):
        D[0,i]=d(pi,x)
        D[1,i] = i
    sort_points = D[0,:].argsort()
    comb = [(i,j,k) for i in sort_points for j in sort_points for k in sort_points if isInTriangle(C[:,i], C[:,j], C[:,k], x)]
    A = np.zeros((4,len(comb)))
    for n,c in enumerate(comb):
        i,j,k = c
        pi = C[:,i]
        pj = C[:,j]
        pk = C[:,k]
        a = area_tri(pi, pj, pk)
        if a==0:
            print((pi,pj,pk))
        A[0,n] = i
        A[1,n] = j
        A[2,n] = k
        A[3,n] = a
    n_min = np.argmin(A[3,:])
    v = A[:,n_min]
    i1 = int(v[0])
    i2 = int(v[1])
    i3 = int(v[2])
    p1 = C[:,i1]
    p2 = C[:,i2]
    p3 = C[:,i3]

    A1 = area_tri(p1, p2, x)
    A2 = area_tri(p1, p3, x)
    A3 = area_tri(p2, p3, x)
    A = area_tri(p1, p2, p3)
    qx = (A1*q[i1]+A2*q[i2]+A3*q[i3])[0]/A
    print(f"{qx = }")

    


