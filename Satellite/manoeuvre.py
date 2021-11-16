import numpy as np
from scipy.optimize import minimize
import matplotlib.pyplot as plt


def Vp(Ra,Rp):
    return np.sqrt((2*µ*Rp)/((Ra+Rp)*Ra))
def Va(Ra,Rp):
    return np.sqrt((2*µ*Ra)/((Ra+Rp)*Rp))

def dv(i):
    return np.sqrt(V1**2+V2**2-2*np.cos(np.radians(7)-np.radians(i))*V1*V2)+np.sqrt(V3**2+V4**2-2*np.cos(np.radians(i))*V3*V4) 

µ = 398690000000000
V1 = 7558.86
V2 = 9900.58
Rt = 6625000
V3 = Va(Rt+600e3,Rt+36000e3)
V4 = Va(Rt+36000e3,Rt+36000e3)
# V3 = 1638.53
# V4 = 3074.6


