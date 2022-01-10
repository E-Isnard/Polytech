clear -a;
clc;

function u=f(s)
  global mu;
  global VT;
  u = VT*(s.^2./(s.^2+(1-s).^2/mu));
endfunction

function u=F(S)
  global L;
  global N;
  global h;
  n = length(S);
  u = zeros(n,1);
  u(1) = f(1)-f(S(1));
  u(2:end) = f(S(1:end-1))-f(S(2:end));
  u = u'/h;
endfunction

function u=fp(s)
  global mu;
  global VT;
  u = VT*((2/mu*s.*(1-s).^2+2/mu*s.^2.*(1-s).^2)./(s.^2+(1-s).^2/mu).^2);
endfunction

function M=dF(S)
  global h;
  v = fp(S);
  M = -diag(v)+diag(v(1:end-1),-1);
  M = M/h;
endfunction

function y=Eimpl(x,dt)
  global N
  omega = 0.1;
  kmax=100;
  eps=1e-8;
  y=x;
  r = y-x-dt*F(y);
  k=0;
  nr = norm(r);
  nr0 = nr;
  while (nr/nr0>eps && k<=kmax)
    dG = eye(N,N)-dt*dF(y);
    dy = -dG\r';
    alpha = min(1,omega/norm(dy,"inf"));
    y = y+alpha*dy';
    r = y-x-dt*F(y);
    nr = norm(r);
    k = k+1;
  endwhile
endfunction

global L=1000;
global T=3600 * 24 * 360 * 20;
global VT=1e-6;
global mu=10;
global N=200
global h = L/N
L
T
N
h
mu
VT
##s_vec = linspace(0,1,100);
##y = fp(s_vec);
##lip = max(fp(s_vec))
lip = 2.87705059769095e-6;
x = 0:h:(L-h);
x = x+h/2;
Nt = floor(lip*T/(h))+1
##Nt=200
dt = T/Nt
S = zeros(Nt,N);
S2 = zeros(Nt,N);
for i=1:Nt-1
    S(i+1,:)=S(i,:)+dt*F(S(i,:));
    S2(i+1,:)=Eimpl(S2(i,:),dt);
endfor

for i=1:Nt
  if (mod(i,20)==0)
    plot(x,S(i,:),"b")
    hold on
    plot(x,S2(i,:),"r")
##    hold off
    title("s(x,t)","fontsize",20)
    xlabel("x","fontsize",20)
    ylabel("s","fontsize",20)
    l = legend(["Euler explicite";"Euler implicite"]);
    set(l, "fontsize", 16);
    pause(0.001);
 endif

endfor