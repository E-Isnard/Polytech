clear all,close all,clc
L=5;
nu=1;
dx = 0.001;
nx = 10;
dtV=10.^[-7,-8,-9];
nt = 10;
theta = 1/2;
err = [];

for dt=dtV
    

 
cfl = nu*dt/dx^2;
cfl
A = 2*diag(ones(nx,1))-diag(ones(nx-1,1),1)-diag(ones(nx-1,1),-1);
B = eye(nx)+theta*cfl*A;
C = eye(nx)-(1-theta)*cfl*A;

x = linspace(0,L,nx+2);
u0 = sin(2*pi/L*x)';
u = u0(2:end-1);


for i=1:nt
    u = B\C*u;
%     if mod(i,2) == 0
%         plot(x(2:end-1),u,'r-',x(2:end-1),u0(2:end-1)','b-'), grid on
%         legend('\theta schéma','donnee initiale')
%         title('\theta schéma');
%         pause(0.1)
%     end
end

% calcul de la solution exacte
uexacte = exp(-nu/L*(2*pi)^2*nt*dt)*sin(2*pi/L*x);

% calcul de la norme de l'erreur 
err = [err norm([0;u;0]-uexacte','inf')];
end

polyfit(log(dtV),log(err),1)


loglog(dtV,err,'bx-',dtV,dtV,'r*-',dtV,dtV.^2,'*-'), grid on
title(['Test de la precision temporelle pour \theta=',num2str(theta)])
legend('erreur','dt','dt^2')
xlabel('dt')
ylabel('error')