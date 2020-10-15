clear all,close all,clc
L=5;
nu=1;
dxV = L./[10,20,40,80];
nt = 10;
theta = 1/2;
cfl = 0.01; 
err = [];

for dx=dxV
    
nx = L/dx;
dt = dx^2*cfl;
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

% calcul de la norme de l'erreur 
err = [err norm([0;u;0]-uexacte','inf')];
end


polyfit(log(dxV),log(err),1)
loglog(dxV,err,'bx-',dxV,dxV.^2,'r*-'), grid on
title(['Test de la precision spatiale pour \theta=',num2str(theta)])
legend('erreur','dx^2')
xlabel('dx')
ylabel('error')