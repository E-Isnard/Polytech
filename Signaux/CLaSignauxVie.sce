clc;
clear;
xdel(winsid());

t=linspace(0,4*%pi,200);
p = poly(0,'p'); 

e1 = 0.5.*sin(2*%pi*880*t).*(t>=0);
e2 = 0.5.*sin(2*%pi*1760*t).*(t>=0);
e = e1+e2;
G = 1/(1+(1/2*%pi*1000)*p);
j = sqrt(-1);
sys = syslin('c',G);
s = csim(e,t,sys);
/*
plot(e)
scf()
plot(s)
scf()
*/
bode(sys,10,10000,"Salut a tous")
playsnd(s,4000);

[frq,repf]=repfreq(sys,0.00001,10000);
[mag_db,phi_deg]= dbphi(repf);


disp("Gain en basse fréquence")
disp(mag_db(1))
disp("Gain en haute fréquence")
disp(mag_db($))




