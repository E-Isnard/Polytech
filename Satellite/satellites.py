import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import odeint
from scipy.optimize import fsolve

AU = 149597870690
mu_boddy=1.32712440018e+020
r0=AU
rf=3/2*AU 
u0=0
uf=0
v0=(mu_boddy/r0)**0.5
vf=(mu_boddy/rf)**0.5
m0=1000
T=0.1
g0=9.80665
Isp=3000

#Units
DU=AU
VU=v0
MU=m0
TU=DU/VU
FU=MU*DU/(TU**2)

# Normalized constants
r0N = r0/DU
u0N = u0/DU
v0N = v0/VU
m0N = m0/MU
rfN=rf/DU
ufN=uf/VU
vfN=vf/VU
TN = T/FU
g0N = g0*(TU**2)/DU
muN = mu_boddy/(DU**3)*(TU**2)
IspN = Isp/TU

def m(t):
    m = m0-(T/(g0*Isp))*t*TU
    return m/MU

def dynpol(x,t):
    r = x[0]
    u = x[1]
    v = x[2]
    lr = x[3]
    lu = x[4]
    lv = x[5]
    phi = np.arctan2(lu,lv)
    dx = np.array([
          u,
          v**2/r-muN/(r**2)+(TN/m(t))*np.sin(phi),
          -(u*v)/r+(TN/m(t))*np.cos(phi),
          -lu*(-(v**2)/(r**2)+2*muN/(r**3))-lv*((u*v)/r**2),
          -lr+lv*(v/r),
          -(lu*2*v)/r+(lv*u)/r
          ])
    return dx

def sol(p):
    r0=r0N
    u0=u0N
    v0=v0N
    tf=p[0]
    lr0=p[1]
    lu0=p[2]
    lv0=p[3]
    y0=np.array([r0,u0,v0,lr0,lu0,lv0])
    sol = odeint(dynpol,y0,np.linspace(0,tf,2),atol=1e-6,rtol=1e-6)
    return sol[-1]

def gnultmin(p):
    l = p[1:];
    x = sol(p);
    g = [x[0]-rfN,x[1]-ufN,x[2]-vfN,np.linalg.norm(l)-1];
    return g
#print(m(1))
#print(dynpol([1,1,1,1,1,1],3))
popt = fsolve(gnultmin,np.array([10,1,1,1]))
tf =popt[0]*TU
days = int(tf//(24*3600))
time = tf%(24*3600)
hours = int(time//3600)
time%=3600
minutes=int(time//60)
time%=60
seconds=round(time)

print(f"Le voyage prendra {days} jours {hours} heures {minutes} minutes et {seconds} secondes")
print(f"La masse du satellite sera de {round(m(tf/TU)*MU,2)} kg")
t = np.linspace(0,popt[0],100)
phi_hist = []
for ti in t:
    s = sol([ti,popt[1],popt[2],popt[3]])
    phi = np.arctan2(s[4],s[5])
    phi_hist.append(phi)
#%%
plt.plot(t*TU/86400,np.array(phi_hist)*180/np.pi)
plt.xlabel("t [j]")
plt.ylabel("$\\varphi$[°]")
plt.grid(linestyle="--")
plt.show()

# %%
