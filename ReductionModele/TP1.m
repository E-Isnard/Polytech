warning('off','all');
function TP1
##A=[1 1 1/2; 1 1 1/4; 1/2 1/4 2]
##x0 = [1 ; 0 ; 0];
##x1 = [1 ; 1 ; 1];
##[lmax,xmax] = puissanceItere(A,x0,1000);
##[lmin,xmin] = puissanceItereInverse(A,x0,1000);
##[lr,xr] = rayleight(A,x0,1000);
##[lr2,xr2] = rayleight(A,x1,1000);
##lmax
##lmin
##lr
##lr2
##l = eig(A)

n=100
T0 = zeros(n,n);
T0(1,:)=100;
T0(n,:)=100;
T0(:,1)=100;
T0(:,n)=100;
T=Temp(0.1,T0,10000,n);
clear TP1



endfunction


function [l,xk]=puissanceItere(A,x0,n)
  xk=x0;
  for i=1:n
    xk = A*xk;
    xk = xk/norm(xk);
  endfor
  l = (A*xk./xk)(1);
endfunction

function [l,xk]=puissanceItereInverse(A,x0,n)
  [P,L,U] = lu(A);
  xk=x0;
  for i=1:n
    xk = L\(P*xk);
    xk = U\xk;
    xk = xk/norm(xk);
  endfor
  l = (A*xk./xk)(1);
endfunction

function [l,xk]=rayleight(A,x0,n)
  xk = x0;
  for i=1:n
    mk = (xk'*A*xk);
    xk = (A-mk*eye(size(A)))\xk;
    xk = xk/norm(xk);
  endfor
  l = (A*xk./xk)(1);
endfunction


function T=Temp(dt,T0,nt,ns)
  T=T0;
  for n=1:nt
    for i=2:(ns-1)
      for j=2:(ns-1)
        T(i,j) = (T(i-1,j)+T(i+1,j)+T(i,j-1)+T(i,j+1)-4*T(i,j))*dt+T(i,j);
      endfor
    endfor
    contourf(T)
    pause(0.01)
  endfor
endfunction

