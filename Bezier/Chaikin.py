import numpy as np
import matplotlib.pyplot as plt

d=2

P1 = np.zeros((d,1))
P2 = np.array([1/2,3]).reshape((d,1))
P3 = np.ones((d,1))
P_i = np.concatenate((P1,P2,P3),axis=1)
# plt.scatter(P_i[0],P_i[1])
# plt.show()

nr = 10
t = 1/4
for _ in range(nr):
    new_Points = np.zeros((d,2*P_i.shape[1]-2))
    for i in range(P_i.shape[1]-1):
        Pj = ((1-t)*P_i[:,i]+t*P_i[:,i+1])
        Pk = (t)*P_i[:,i]+(1-t)*P_i[:,i+1]
        new_Points[:,2*i] = Pj
        new_Points[:,2*i+1] = Pk
        # P_i = np.concatenate((P_i,Pj),axis=1)
    P_i = new_Points
plt.plot(P_i[0],P_i[1])
plt.show()