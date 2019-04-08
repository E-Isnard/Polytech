clc;
clear;
xdel(winsid());

A = 0.5;
f1 = 880;
f2 = 1760;
p = poly(0,'p');


t = linspace(0,4*%pi);

e1 = A*sin(2*%pi*f1*t).*(t>0);
e2 = A*sin(2*%pi*f2*t).*(t>0);
G = 1/(1+2*%pi*1000*p);
sys = syslin('c',G);
s1 = csim(e1,t,sys);
s2 = csim(e2,t,sys);

playsnd(s1)
sleep(200)
playsnd(s2)
sleep(200)

bode(sys,0.1,2000,'Diagramme de Bode du système')
//Filtre passe-bas car le gain décroît avec la fréquence

[frq,repf]=repfreq(sys,0.000000001,2000);
[mag,phi]= phasemag(repf)
i = find(mag<-3)
disp("La fréquence de coupure (en Hz) est ")
disp(frq(i(1)))
disp("Avec une magnitude(en dB) de ")
disp(mag(i(1)))

//disp([frq,mag])

//Le premier signal s'entend mieux car il a une fréquence plus basse que le premier et que le systeme est un filtre passe-bas =)




//TD6






