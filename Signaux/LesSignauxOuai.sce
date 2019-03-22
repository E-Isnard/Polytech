clc;
clear;
xdel(winsid());

t = linspace(0,0.2);
t1 = linspace(0,0.5,200);
t2 = linspace(0,3,200);
p = poly(0,'p'); 
G = (10+p)/(20+p);
sys = syslin('c',G);
j = sqrt(-1);

//Question 1

out_ind = csim('step',t,sys);
plot2d(t,out_ind);

disp("Valeur initiale:")
disp(out_ind(1))
disp("Valeur finale:")
disp(out_ind($))


//Question 2

e1 = sin(100*t1);
e2 = sin(14.14*t2);

s1 = csim(e1,t1,sys)
s2 = csim(e2,t2,sys)

title("1ere entree")
scf()
plot2d(t1,e1)
plot2d(t1,s1)

scf()

title("2eme entree")
plot2d(t2,e2)
plot2d(t2,s2)


//Question 3
fmax = 100/(2*%pi);
fmin = 14.14/(2*%pi);
[frq,repf]=repfreq(sys,fmin,fmax);
rep1=repf(1);
rep2=repf(2);


[mag_db,phi_deg]= dbphi(repf)

ph1_deg = phi_deg(1)
ph2_deg = phi_deg($)

mag1_db = mag_db(1)
mag2_db = mag_db($)

ph1_rad = ph1_deg * %pi/180
ph2_rad = ph2_deg * %pi/180

mag1_amp = 10^(mag1_db/20)
mag2_amp = 10^(mag2_db/20)

scf()
bode(sys,0.1,1000,"Salut a tous")







