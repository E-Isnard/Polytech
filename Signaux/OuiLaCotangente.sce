clc
clear
xdel(winsid())
// Coucou c'est Enzo ;) Bienvenue dans le fichier OuiLaCotanente !
t=linspace(0,4*%pi,200);
p = poly(0,'p'); 
G = 1/(1+(5E-3)*p);
E = sin(t).*(t>=0)-sin(t).*(t>=2*%pi);
sys = syslin('c',G);

title("Oui")
xlabel("t[s]");
ylabel("s(t)")

out_imp = csim('impulse',t,sys);
out_E = csim(E,t,sys);
out_ind = csim('step',t,sys);

disp(out_imp($))

title("Réponse impulsionelle");
xlabel("t[s]");
ylabel("s(t)");
plot2d(t,out_imp)

scf()


title("Réponse indicuelle")
xlabel("t[s]");
ylabel("s(t)");
plot2d(t,out_ind)

scf()


title("Réponse au signal E")
xlabel("t[s]");
ylabel("s(t)");
plot2d(t,E)
plot2d(t,out_E)
