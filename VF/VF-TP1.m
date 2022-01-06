clear;
clc;
n_mesh = [4 8 16 32 64 128];
eL2 = [];
for i_mesh=n_mesh
  [x,uh] = solve_laplacian_FV(1,i_mesh);
  u = u_ext(x);
  h = x(2)-x(1);
  e = u-uh;
  eL2 = [eL2 norm(e)/h];


endfor
beta = polyfit(log2(n_mesh),log2(eL2),1)
plot(log2(n_mesh),polyval(beta,log2(n_mesh)),"-xb")
hold on
plot(log2(n_mesh),log2(eL2),"-xr")


function [x,uh]=solve_laplacian_FV(L,N)

h = L/(N);
x = 0:h:(L-h);
x = x+h/2;
uD = 1;
g = -u_ext_p(L);
Ah = zeros(N,N);
Fh = zeros(N,1);

for i=2:N-1
  Fh(i) = h*f(x(i));
  Ah(i,i) = 2/h;
  Ah(i,i+1) = -1/h;
  Ah(i,i-1) = -1/h;
endfor
Fh(1) = h*f(x(1))+2*uD/h;
Fh(N) = h*f(x(end))-g;
Ah(1,1) = 1/h+2/h;
Ah(1,2) = -1/h;
Ah(N,N) = 1/h;
Ah(N,N-1) = -1/h;

uh = Ah\Fh;

endfunction
function y=f(x)
  y = pi^2*(sin(pi*x)-cos(pi*x).^2).*exp(sin(pi*x));
endfunction

function y=u_ext(x)
  y = exp(sin(pi*x));
endfunction

function y=u_ext_p(x)
  y = pi.*cos(pi.*x).*exp(sin(pi.*x));
endfunction
