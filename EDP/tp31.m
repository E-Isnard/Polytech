V=0.8;
L = 10;
T = 1;
dt = 0.01;
dx = 0.01;
x = 0:dx:L;
t = 0:dt:T;
s = V*dt/dx;
u0 = double((x>1) & (x<1.5));
u=u0;
N = length(x);

for n=1:N
   uold = u;
   u(2:N) = uold(2:N)-s*(uold(2:N)-uold(1:N-1));
   u(1)=u(N);
        

end

condit = @(x) double((x>1) & (x<1.5));
uexact = condit(x-V*T);


plot(x,u,'r',x,uexact,'g',x,u0,'k'), grid on
title('Comparaison entre la solution exacte et approchee')
legend('Sol. approchee’,’Sol. exacte','condition initiale')
xlabel('x')
ylabel('Solution')






        
        
        