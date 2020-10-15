clear all,close all,clc
L=5;
nu=1;
dx = 0.05;
nx = L/dx;
cfl = 0.1;  
dt = dx^2*cfl;
nt = 10;
theta = 1;
A = 2*diag(ones(nx,1))-diag(ones(nx-1,1),1)-diag(ones(nx-1,1),-1);
B = eye(nx)+theta*cfl*A;
C = eye(nx)-(1-theta)*cfl*A;

x = linspace(0,L,nx+2);
u0 = sin(2*pi/L*x)';
u = u0(2:end-1);


for i=1:nt
    u = B\C*u;
%     if mod(i,10) == 0
%         plot(x(2:end-1),u,'r-',x(2:end-1),u0(2:end-1)','b-'), grid on
%         legend('\theta schéma','donnee initiale')
%         title('\theta schéma');
%         pause(0.1)
%     end
end

% calcul de la solution exacte
uexacte = exp(-nu/L*(2*pi)^2*nt*dt)*sin(2*pi/L*x);

% comparaison au temps final
plot(x,[0;u;0],'r-',x,uexacte,'b-'), grid on
legend('\theta schéma','solution exacte')
title('\theta schéma');

% calcul de la norme de l'erreur 
norm([0;u;0]-uexacte','inf')