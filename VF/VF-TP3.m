##//
##//    Stratigraphic model with diffusive sediment transport 
##//    Modelize the formation of sedimentary basins at large space and time scales 
##//
##//    Single lithology Model 
##//
##//
##//  d_t h(x,t) + div(grad(psi(b(x,t)))) = 0 on (0,Lx)x(0,tf)
##//
##//  h(x,0) = hinit(x)   on (0,Lx)
##//
##//  grad(psi).n = g0 for x=0,  grad(psi).n = g1 for x = Lx  
##//
##//  b(x,t) = hsea(t) - h(x,t)
##//
##//
##//  Finite Volume discretization using TPFA fluxes on unstructured meshes 
##//
##//  Ouputs: * h(x,t), b(x,t)
##//
##//   
##//          * hs(x,t) = min_(t<=s<=tf) h(x,s) (sediment layers at each time t, taking into account erosions)
##//


clear
clc

##//data
global Lx=2;
##//space discretization
global N=100;
global Nint = N-1;
global Nbound = 2;
dx=Lx/N;

#//time discretization
tf = 1.5; #// final simulation time
#//tf = 0.16; // final simulation time
dtinit = 0.02; #// initial time step
dtmax = 0.02; #// maximum time step 
Ndtmax = 10000; #// maximum number of time step


##//tf = 0.5; // final simulation time
##//dtinit = 0.002; // initial time step
##//dtmax = 0.002; // maximum time step 
##//Ndtmax = 10000; // maximum number of time step

##//Newton convergence 
Newtmax = 10; #// maximum number of Newton iterations 
eps=1.0E-6; #//  stopping criteria 

global Km;
global Kc;
Km = 1;
Kc = 10;

g0 = -20;
g1 = 0;

function s = f_hsea(t)
    s = 25 + 5*cos(12*t) ;
endfunction

function s = f_psi(u)
    global Kc;
    global Km;
    if (u<0)
        s = Kc*u;
    else 
        s = Km*u;
    end
endfunction

function s = f_psip(u)
    global Kc;
    global Km;
    if (u<0)
        s = Kc;
    else 
        s = Km;
    end
endfunction


function s = f_hinit(x)
  global Lx;
  s = 25*exp(-8*x/Lx) + 10;
endfunction


function R = residual(h,b,h0,dt)
global N;
global volume;
global Nint;
global Nbound;
global cellint;
global fbound;
global cellbound;
global T;
R = zeros(N,1);
for m=1:N
  R(m) = volume(m)*(h(m)-h0(m))/dt;
endfor

for i=1:Nint
  i1 = cellint(i,1);
  i2 = cellint(i,2);
  R(i1) = R(i1) + T(i)*(f_psi(b(i2))-f_psi(b(i1)));
  R(i2) = R(i2) - T(i)*(f_psi(b(i2))-f_psi(b(i1)));
endfor

for j=1:Nbound
  jb = cellbound(j);
  R(jb) = R(jb) + fbound(j);
endfor
  
endfunction


function A = Jacobian(b,dt)
  global N
  global Nint
  global cellint
  global T
  global volume

  A = zeros(N,N);
  for m=1:N
    A(m,m) = volume(m)*1/dt;
  endfor
  for i=1:Nint
  m1 = cellint(i,1);
  m2 = cellint(i,2);
  dm1_flux12 = T(i)*f_psip(b(m1));
  dm2_flux12 = T(i)*f_psip(b(m2));
  A(m1,m1) = A(m1,m1)+dm1_flux12;
  A(m1,m2) = A(m1,m2)+dm2_flux12;
  A(m2,m1) = A(m2,m1)-dm1_flux12;
  A(m2,m2) = A(m2,m2)-dm2_flux12;
  
  endfor
  
##// A COMPLETER 
endfunction


##
##//data structure for the uniform 1D mesh of size dx of the domain (0,Lx)
##// cells m = 1:N
global volume
for m=1:N
  volume(m) = dx;
  X(m) = dx*(m-1/2);
end  

##//Interior faces: i = 1:N-1
global cellint;
for i=1:Nint
  cellint(i,1) = i;
  cellint(i,2) = i+1;
  surfaceint(i) =  1;
end

##//Boundary
global cellbound;
global fbound;
cellbound(1) = 1;
cellbound(2) = N;
fbound(1) = g0;
fbound(2) = g1;


##//transmissibilities of interior faces
global T;
for i=1:Nint
  T(i) = surfaceint(i)/abs(X(cellint(i,2))-X(cellint(i,1)));
endfor



#//initial sediment thickness
for k=1:N
 h0(k)= f_hinit(X(k));    
endfor

hs(:,1) = h0;

dt = dtinit;
time(1) = 0;
timestep(1) = dt;

figure(2);
title('h')
plot (X,h0,'r')  


##//Initialization of Newton ------------------------
h = h0;

##//time loop -----------------------------------------------------------
t = 0;
n = 1;
newt = 1;

while ((t<tf)&(n<Ndtmax))
n = n + 1;
t = t + dt
hsea = f_hsea(t);

b = ones(N)*hsea - h; #// bathymetry

R = residual(h,b,h0,dt); #// residual 

##//Initial residual norm 
normR = norm(R);
normR0 = normR;


##//Newton loop -----------------------------------
itn = 1;
xn(itn) = newt;
yn(itn) = 1;

while ((normR/normR0>eps)&(itn<Newtmax))

itn = itn + 1;
newt = newt + 1;
xn(itn) = newt;
abs(X(cellint(i,2))-X(cellint(i,1)));
endfor



A = Jacobian(b,dt); #//Jacobian matrix A = dR/dh(h)


dh = -A\R; #//solution dh 

h = h + dh'; #//Newton update 


##//residual  
b = ones(N)*hsea - h;
R = residual(h,b,h0,dt);
normR = norm(R);#//residual norm  
yn(itn) = normR/normR0;

end


if (itn>Newtmax-1) #// if time step refused (Newton not converged)
## //abort
 h = h0;
 t = t-dt;
 dt = dt/2
 restart = 1
 n = n -1;
else 
 restart = 0;  
end  

 

if (restart==0) #// if time step accepted 
 
 h0 = h; 
 hs(:,n) = h;
 time(n) = time(n-1) + dt;
 timestep(n) = dt;
 dt = 1.2*dt;
 if (dt>dtmax)
  dt = dtmax;
 end
 if (t+dt>tf)
  dt = tf-t;
 end


## //plot h 
figure(2);
title('h') 
if (int8(n/1)*1==n)
  plot(X,h,'-r')  
  hold on
end
 
 
end #// end if restart = 0

##//Newton convergence
figure(1);
epsm = 1E-14;
plot(xn,log10(yn+epsm),'r-*')
hold on
clear xn;
clear yn;


end #// end time loop ----------------------------------------------------------------

## computation of hs taking out erosions 
for k=1:N
  for j=n-1:-1:1
    hs(k,j) = min(hs(k,j),hs(k,j+1));
  endfor
endfor

figure(4);
title('hs')
for it=1:n
plot(X,hs(:,it),'-r')
endfor