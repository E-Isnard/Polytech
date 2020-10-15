clear all,close all,clc
L=10;
dx = 0.05;
nx = L/dx;
cfl = 0.48;  
dt = dx^2*cfl;
nt = 300;
theta = 1;
A = 2*diag(ones(nx,1))-diag(ones(nx-1,1),1)-diag(ones(nx-1,1),-1);
B = eye(nx)+theta*cfl*A;
C = eye(nx)-(1-theta)*cfl*A;

x = linspace(-L,L,nx+2);
u0 = max(0,1-x.^2)';
u = u0(2:end-1) ;

for i=1:nt
    u = linsolve(B,C*u);
    if mod(i,5) == 0
        plot(x(2:end-1),u,'r-',x(2:end-1),u0(2:end-1)','b-'), grid on
        legend('\theta schéma','donnee initiale')
        title('\theta schéma');
        pause(0.1)
    end
end
