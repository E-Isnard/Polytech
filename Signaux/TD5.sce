//ex1

clc;
clear;
xdel(winsid());

t = linspace(0,50)
p = poly(0,'p')
G1 = 1/2*1/(p+1)
//disp(G1)
G2 = 1/10*1/(p+10)
//disp(G2)
G3 = G1*G2

sys1 = syslin('c',G1)
sys2 = syslin('c',G2)
sys3 = syslin('c',G3)

s1 = csim('step',t,sys1)
s2 = csim('step',t,sys2)
s3 = csim('step',t,sys3)


[frq1,repf1]=repfreq(sys1,1/(2*%pi),10);
[frq2,repf2]=repfreq(sys2,1/(2*%pi),10);
[frq3,repf3]=repfreq(sys3,1/(2*%pi),10);


[mag1,phi1]= dbphi(repf1)
[mag2,phi2]= dbphi(repf2)
[mag3,phi3]= dbphi(repf3)

disp("Systeme 1")
disp("Initial")
disp(s1(1))
disp("Finale")
disp(s1($))
disp("Mag")
disp(mag1(1))
disp("phi")
disp(phi1(1))

disp("")
disp("")

disp("Systeme 2")
disp("Initial")
disp(s2(1))
disp("Finale")
disp(s2($))
disp("Mag")
disp(mag2(1))
disp("phi")
disp(phi2(1))


disp("")
disp("")


disp("Systeme 3")
disp("Initial")
disp(s3(1))
disp("Finale")
disp(s3($))
disp("Mag")
disp(mag3(1))
disp("phi")
disp(phi3(1))

/*
bode(sys1,0.1,2000,"Systeme 1")
scf()
bode(sys2,0.1,2000,"Systeme 2")
scf()
bode(sys3,0.1,2000,"Systeme 3")
*/

bode([sys1;sys2;sys3],0.1,2000,["Systeme1";"Systeme2";"Systeme3"])


//TD6

K1 = s1($);
K2 = s2($);

i1 = find(abs(s1(t)-K1)/K1<0.05);
i2 = find(abs(s2(t)-K2)/K2<0.05);

disp(i1);
disp(i2);












