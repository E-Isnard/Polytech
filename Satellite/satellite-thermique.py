import numpy as np
from scipy.optimize import fsolve

eps=0.85
alpha=0.2
phiS = 560
Tesp = 3
sigma=5.67e-8
Gl=30
lamb = 1.3
e = 3e-2
Tamb = 300
S=6
Pint=2500

def f(T):
    """
    T[0]: Ts_ext
    T[1]: Ts_int
    T[2]: Tn_ext
    T[3]: Tn_int
    """
    return np.array([
        eps*S*sigma*(Tesp**4-T[0]**4)+lamb*S/e*(T[1]-T[0])+alpha*S*phiS,
        eps*S*sigma*(Tamb**4-T[1]**4)+lamb*S/e*(T[0]-T[1])+Pint+Gl*(T[3]-T[1]),
        eps*S*sigma*(Tesp**4-T[2]**4)+lamb*S/e*(T[3]-T[2]),
        eps*S*sigma*(Tamb**4-T[3]**4)+lamb*S/e*(T[2]-T[3])+Pint+Gl*(T[1]-T[3])
    ])

T = fsolve(f,np.ones((4,1))*100)
print(f"Ts_ext={T[0]}")
print(f"Ts_int={T[1]}")
print(f"Tn_ext={T[2]}")
print(f"Tn_int={T[3]}")
