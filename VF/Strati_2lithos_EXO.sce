//
//    Stratigraphic model with diffusive sediment transport 
//    Modelize the formation of sedimentary basins at large space and time scales 
//
//    Two lithology Model 
//
//
//  d_t \int_0^h(x,t) ci(x,z,t)dz + div(cis(x,t) k_i grad(psi(b(x,t)))) = 0 on (0,Lx)x(0,tf), i=1,2
//
//  h(x,0) = hinit(x)   on (0,Lx)
//
//   cis(x,t)*ki*grad(psi).n = g0i for x=0,  grad(psi).n = 0 for x = Lx  

//  b(x,t) = hsea(t) - h(x,t)
//
//
//  d_t c_i(x,z,t) = 0 for all z<h(x,t)
//      c_i(x,h(x,t),t) = cis(x,t) if d_t h(x,t) > 0
//      c_i(x,z,0) = ci0(x,z) for z < hinit(x)
//
//
//  Finite Volume discretization using TPFA fluxes on unstructured meshes and upwinding of the cis 
//
//  The linear advection equation on each column x is integrated exactly on the moving 1D mesh using the discrete data 
//   cis(x,t) and h(x,t)
//
//
//  Ouputs: * h(x,t), b(x,t)
//
//   
//          * hs(x,t) = min_(t<=s<=tf) h(x,t) (sediment layers at each time t, taking into account erosions)
//
//          * ci(x,z,tf)  concentrations in the basin at final time tf 
//


clear

//data
Lx=2;
//space discretization
N=100;
Nint = N-1;
Nbound = 2;
dx=Lx/N;

// initial composition cinit of litho 1 assumed constant for z<hinit(x)
cinit = 0.5;


//time discretization
tf = 1.5; // final simulation time
dtinit = 0.02; // initial time step
dtmax = 0.02; // maximum time step 
Ndtmax = 10000; // maximum number of time step


//Newton convergence 
Newtmax = 30; // maximum number of Newton iterations 
eps=1.0E-6; //  stopping criteria 


Km = 1;
Kc = 10;  // Kc/Km ratio of diffusion coefficients between continental and marine environments 

Ki(1) = 1; // K(1)/K(2) = ratio of diffusion coefficients of litho 1 and 2 
Ki(2) = 5;

g0(1) = -10;
g0(2) = -10;


function s = f_hsea(t)
    s = 25 + 5*cos(12*t) ;
endfunction

function s = f_psi(u)
    if (u<0) then
        s = Kc*u;
    else 
        s = Km*u;
    end
endfunction

function s = f_psip(u)
    if (u<0) then
        s = Kc;
    else 
        s = Km;
    end
endfunction


function s = f_hinit(x)
    s = 25*exp(-8*x/Lx) + 10;
endfunction


function [R,fluxint,upwind] = residual(h,b,ncol,hcol,ccol,cs,dt)
// inputs 
// dt 
// h(1:N), b(1:N), cs(1:N)    
// ncol(k): number of cells in z: ]-infty,hcol(k,1)], ... ,[hcol(k,ncol(k)-1),hcol(k,ncol(k))]
// ccol(k,l), l=1,ncol(k): concentration of litho 1 in the cell l = 1,...,ncol(k)
        
// compute the residuals in each cell
// R(k,i)  equation i = 1,2 in cell k=1,...,N 
// compute the inner fluxes fluxint(1:Nint) and the upwind cells upwind(1:Nint) at each inner face i=1,...,Nint

R = zeros(N,2);

// accumulation term in each cell 
for k=1:N    
 
 s1 = 0;
 s2 = 0;
        
 if (h(k)>=hcol(k,ncol(k))) then     
      s1 = s1 + cs(k)*(h(k)-hcol(k,ncol(k)));   
      s2 = s2 + (1-cs(k))*(h(k)-hcol(k,ncol(k)));         
  else 
      l = ncol(k)
      while ( (l>=2)&( h(k) < hcol(k,l-1) ) ) then            
        s1 = s1 + ccol(k,l)*(hcol(k,l-1)-hcol(k,l));   
        s2 = s2 + (1-ccol(k,l))*(hcol(k,l-1)-hcol(k,l));                         
        l = l-1          
      end
      s1 = s1 + ccol(k,l)*(h(k)-hcol(k,l));   
      s2 = s2 + (1-ccol(k,l))*(h(k)-hcol(k,l));               
 end     

 R(k,1) = s1*volume(k)/dt;
 R(k,2) = s2*volume(k)/dt;
end


for k=1:N
 psi(k) = f_psi(b(k));
end

// inner fluxes and upwind cells
for i=1:Nint
  k1 = cellint(i,1);
  k2 = cellint(i,2);    
  fluxint(i) = Tint(i)*(psi(k2) - psi(k1))
  if (fluxint(i)>0) then 
      upwind(i) = k1 
  else 
      upwind(i) = k2
  end  
end

// inner fluxes
for i=1:Nint
  k1 = cellint(i,1);
  k2 = cellint(i,2);
  kup = upwind(i);  
  flux1 = Ki(1)*cs(kup)*fluxint(i)
  flux2 =  Ki(2)*(1-cs(kup))*fluxint(i)   
  R(k1,1) = R(k1,1)  + flux1
  R(k1,2) = R(k1,2) + flux2
  R(k2,1) = R(k2,1)   -flux1
  R(k2,2) = R(k2,2) - flux2
  
  
end
k1 = cellbound(1)
R(k1,1) = R(k1,1) + g0(1)
R(k1,2) = R(k1,2) + g0(2)


endfunction


function A = Jacobian(h,b,ncol,hcol,ccol,cs,dt,fluxint,upwind)
// Jacobian of R wrt to h and cs
//
// Ordering:     
//eq k,1: 2*k-1, eq k,2: 2*k, unknown h(k): 2*k-1, unknown cs(k): 2*k 

A=zeros(2*N,2*N);

// accumulation term in each cell 
for k=1:N    
 if (h(k)>=hcol(k,ncol(k))) then 

//    eqs k lithos 1 et 2 par rapport a hk      
      A(2*k-1,2*k-1) = A(2*k-1,2*k-1)    + cs(k)*volume(k)/dt; 
      A(2*k,2*k-1)   = A(2*k,2*k-1)      + (1-cs(k))*volume(k)/dt;         

//    eqs k lithos 1 et 2 par rapport a csk         
      s = (h(k)-hcol(k,ncol(k)))*volume(k)/dt;  
      A(2*k-1,2*k) = A(2*k-1,2*k)    + s; 
      A(2*k,2*k)   = A(2*k,2*k)      - s;
      
  else      
      l = ncol(k)
      while ( (l>=2)&( h(k) < hcol(k,l-1) ) ) then                 
       l = l-1          
      end  
//    eqs k lithos 1 et 2 par rapport a hk      
      A(2*k-1,2*k-1) = A(2*k-1,2*k-1) + ccol(k,l)*volume(k)/dt;   
      A(2*k,2*k-1)   = A(2*k,2*k-1)      + (1-ccol(k,l))*volume(k)/dt;                      
 end      
end

for i=1:Nint
  k1 = cellint(i,1);
  k2 = cellint(i,2);
  kup = upwind(i);  
  flux1 = Ki(1)*cs(kup)*fluxint(i)
  flux2 =  Ki(2)*(1-cs(kup))*fluxint(i)   
  flux1_cskup = Ki(1)*fluxint(i)
  flux2_cskup = -Ki(2)*fluxint(i)
  flux1_hk1 = Ki(1)*cs(kup)*Tint(i)*f_psip(b(k1))
  flux1_hk2 = -Ki(1)*cs(kup)*Tint(i)*f_psip(b(k2))
  flux2_hk1 = Ki(2)*(1-cs(kup))*Tint(i)*f_psip(b(k1))
  flux2_hk2 = -Ki(2)*(1-cs(kup))*Tint(i)*f_psip(b(k2))
  
  
  A(2*k1-1,2*kup) = A(2*k1-1,2*kup) + flux1_cskup
  A(2*k1-1,2*k1-1) = A(2*k1-1,2*k1-1) + flux1_hk1
  A(2*k1-1,2*k2-1) = A(2*k1-1,2*k2-1) + flux1_hk2
  
  
  A(2*k1,2*kup) =  A(2*k1,2*kup) + flux2_cskup
  A(2*k1,2*k1-1) =  A(2*k1,2*k1-1) + flux2_hk1
  A(2*k1,2*k2-1) =  A(2*k1,2*k2-1) + flux2_hk2
  
  A(2*k2-1,2*kup) = A(2*k2-1,2*kup) - flux1_cskup
  A(2*k2-1,2*k1-1) = A(2*k2-1,2*k1-1) - flux1_hk1
  A(2*k2-1,2*k2-1) = A(2*k2-1,2*k2-1) - flux1_hk2
  
  
  A(2*k2,2*kup) =  A(2*k2,2*kup) - flux2_cskup
  A(2*k2,2*k1-1) =  A(2*k2,2*k1-1) - flux2_hk1
  A(2*k2,2*k2-1) =  A(2*k2,2*k2-1) - flux2_hk2
 
  
end 


// A COMPLETER 

endfunction



//data structure for the uniform 1D mesh of size dx of the domain (0,Lx)
// cells m = 1:N
for m=1:N
  volume(m) = dx;
  X(m) = (m-1/2)*dx;
end  

//Interior faces: i = 1:N-1
for i=1:Nint
  cellint(i,1) = i;
  cellint(i,2) = i+1;
  surfaceint(i) = 1;  
  Xint(i) = i*dx;
end
cellbound(1) = 1;
cellbound(2) = N;



//transmissibilities of interior faces
for i=1:Nint
  k1 = cellint(i,1);
  k2 = cellint(i,2);
  Tint(i) = surfaceint(i)/abs(X(k2)-X(k1));
end

/////////////////////////////////

// initial sediment thickness 
for k=1:N
    h0(k) = f_hinit(X(k)); 
end


hs(:,1) = h0; // to compute erosions for output 


//init of the columns ccol,hcol,ncol 
// ncol(k): number of cells in z: ]-infty,hcol(k,1)], ... ,[hcol(k,ncol(k)-1),hcol(k,ncol(k))]
// ccol(k,l), l=1,ncol(k): concentration of litho 1 in the cell l = 1,...,ncol(k)

for k=1:N
    ncol(k) = 1;
    ccol(k,ncol(k)) = cinit;
    hcol(k,ncol(k)) = h0(k); 
end

dt = dtinit;
time(1) = 0;
timestep(1) = dt;

scf(2);
title('h')
plot (X,h0,'r')  


//Initialization of Newton at t = 0 ------------------------
cs = ones(N,1)*cinit; // initial guess 
cs0 = cs; // will be used if time step is restarted 

//time loop -----------------------------------------------------------
t = 0;
n = 1;
newt = 1;

while ((t<tf)&(n<Ndtmax))
n = n + 1;
t = t + dt
hsea = f_hsea(t);


h = h0 + 1E-3*dt; //init Newton with sedimentation 

b = ones(N,1)*hsea - h; // bathymetry 

[R,fluxint,upwind]  = residual(h,b,ncol,hcol,ccol,cs,dt); // residual 

//Initial residual norm 
normR = norm(R(:,1)) + norm(R(:,2));
normR0 = normR;


//Newton loop -----------------------------------
itn = 1;
xn(itn) = newt;
yn(itn) = 1;

while ((normR/normR0>eps)&(itn<Newtmax)) do 

itn = itn + 1;
newt = newt + 1;
xn(itn) = newt;


A = Jacobian(h,b,ncol,hcol,ccol,cs,dt,fluxint,upwind); 



// correction of the Jacobian and residual 
for k=1:N
    epsA = 1E-10;
    if (abs(A(2*k,2*k))<epsA) then
// csk indeterminee 
// on garde la somme des equations de la maille k et on met l'equation 2 -> csk = 0        
        R(k,1) = R(k,1)+R(k,2);
        R(k,2) = 0;
        
        A(2*k-1,:) = A(2*k-1,:) + A(2*k,:)
        A(2*k,:) = 0;
        A(2*k,2*k) = 1        
        
    end
end

for k=1:N
    B(2*k-1) = -R(k,1);
    B(2*k)   = -R(k,2);
end

dU = A\B; //solution dU 

dhmax = 0;
for k=1:N
    dh(k) = dU(2*k-1);
    dcs(k) = dU(2*k);
end

for k=1:N //Newton update 
    
 h(k)  = h(k)  + dh(k); 
 
 cs(k) = cs(k) + dcs(k);
 
 if (cs(k)<0) then // projection of cs on [0,1]
     cs(k) = 0;
 elseif (cs(k)>1) then 
     cs(k) = 1;
 end
end 

b = ones(N)*hsea - h; // bathymetry 

[R,fluxint,upwind]  = residual(h,b,ncol,hcol,ccol,cs,dt);//residual 

normR = norm(R(:,1)) + norm(R(:,2)); //residual norm  

yn(itn) = normR/normR0;

end //end Newton loop ---------------------


//Newton convergence
scf(1);
epsm = 1E-14;
plot(xn,log10(yn+epsm),'b-*')
clear xn;
clear yn;


if (itn>Newtmax-1) // if time step refused (Newton not converged)
 h = h0;
 cs = cs0;
 t = t-dt;
 dt = dt/2
 restart = 1
 n = n -1;
else 
 restart = 0;  
end  

 

if (restart==0) // if time step accepted 
 
     
 h0 = h; 
 cs0 = cs;
 
 hs(:,n) = h; // storage of h in hs 
 
 // update of ncol, hcol, ccol 
 
 for k=1:N
  if (h(k)>=hcol(k,ncol(k))) then     
     ncol(k) = ncol(k) + 1;
     hcol(k,ncol(k)) = h(k);
     ccol(k,ncol(k)) = cs(k);         
   else      
      l = ncol(k)
      while ( (l>=2)&( h(k) < hcol(k,l-1) ) ) then                    
      l = l-1          
      end      
      ncol(k) = l;
      hcol(k,ncol(k)) = h(k);              
  end   
 end
 

     
 time(n) = time(n-1) + dt;
 timestep(n) = dt;
 

 dt = 1.2*dt;
 if (dt>dtmax)
  dt = dtmax;
 end
 if (t+dt>tf)
  dt = tf-t;
 end


 //plot h 
 scf(2);
 title('h')
 if (int(n/1)*1==n)
  plot (X,h,'-b')  
 end
 
 
end // end if restart = 0



end // end time loop ----------------------------------------------------------------

// computation of hs taking out erosions 
for k=1:N
    for i=n-1:-1:1
        hs(k,i) = min(hs(k,i),hs(k,i+1));  
    end
end

scf(4)
title('hs')
for it=1:n
 plot(X,hs(:,it),'-b')
end


  //wells for postprocessing  
  k1 = int(N/4);
  k2 = int(N/2);
  k3 = int(3*N/4);
   

  //plot concentration c1(xkw,z,tf) at well xkw 
  scf(6);
  title(' paleo concentrations at wells ')
  plot (hcol(k1,1:ncol(k1))',ccol(k1,1:ncol(k1))','-xr') 
  plot (hcol(k2,1:ncol(k2))',ccol(k2,1:ncol(k2))','-xb') 
  plot (hcol(k3,1:ncol(k3))',ccol(k3,1:ncol(k3))','-xm') 
  


  
  




