clc
clear
t=linspace(0,5,100)
u =1.*(t>=0)
R=400*1E3
C = 1E-6
a = -1/(R*C)
b = 1/R
q_0 = 500*1E-9

sys=syslin('c',a,b,1)

q_sim=csim('impuls',t,sys)

plot2d(t,q_sim)

disp(q_sim(1))


disp(q_sim($))

