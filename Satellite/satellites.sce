clear,clc

//C is a struct containing all the constants

//Constants
C.a=1795.5e3;
C.e=0.002367;
C.AU = 149597870690;
C.r0=C.a*(1-C.e);
C.rf=1738E3;
C.m0=640;
C.T=1600;
C.g0=9.80665;
C.Isp=325;
C.mu_boddy=4902799229378.29;

//Units
C.DU=C.a;
C.VU=(C.mu_boddy/C.a)^0.5;
C.MU=C.m0;
C.TU=C.DU/C.VU;
C.FU=C.MU*C.DU/C.TU^2;

//Normalized constants
C.r0N = C.r0/C.DU;
C.u0N = 0;
C.m0N = C.m0/C.MU;
C.v0N = sqrt(C.mu_boddy/C.a*(1+C.e)/(1-C.e))/C.VU;
C.rfN=C.rf/C.DU;
C.TN = C.T/C.FU;
C.g0N = C.g0*C.TU^2/C.DU;
C.muN = C.mu_boddy/C.DU^3*C.TU^2;
C.IspN = C.Isp/C.TU;
C.vfN = 0;

function m=m(t)
    m = C.m0-(C.T/(C.g0*C.Isp))*t*C.TU;
    m = m/C.MU;
endfunction

//C.TN=0.01;
//T.IspN=1;
//T.muN = 1;
//T.g0N = 1;

function dx=dynpol(t,x)
    r = x(1);
    u = x(2);
    v = x(3);
    lr = x(4);
    lu = x(5);
    lv = x(6);
    disp("phi")
    phi = atan(lu,lv);
    disp(phi)
    dx = [
          u;
          v^2/r-C.muN/r^2+C.TN/m(t)*sin(phi);
          -(u*v)/r+C.TN/m(t)*cos(phi);
          -lu*(-v^2/r^2+2*C.muN/r^3)-lv*(u*v/r^2);
          -lr+lv*v/r;
          -lu*2*v/r+lv*u/r
          ];
    
endfunction

//Compute the solution of x'=dynpol(t,x) at time tf, p = [tf,lr0,lu0,lv0],x=[r,u,v,lr,lu,lv]
function sol=sol(p)
    r0=C.r0N;
    u0=C.u0N;
    v0=C.v0N;
    tf=p(1);
    lr0=p(2);
    lu0=p(3);
    lv0=p(4);
    y0 = [r0;u0;v0;lr0;lu0;lv0];
    sol = ode(y0,0,tf,1e-6,1e-6,dynpol);
    
endfunction

function g=gnultmin(p)
    lambda = p(2:$);
    x = sol(p);
    g = [x(1)-C.rfN;x(2);x(3)-C.vfN;lambda'*lambda-1];
endfunction


//disp(dynpol(3,[1;1;1;1;1;1]))
// disp(gnultmin([1;1;1;1]))
// [popt,fval,info]= fsolve([1;1;1;1],gnultmin);
// //disp(dynpol(0,[C.r0N,C.u0N,C.v0N,-1,0,0]))
// //disp(gnultmin([3;0.6;0.3;0.7]))
// //disp(popt)
// //disp(C)

// disp(string(popt(1)*C.TU/86400)+" jours")
// disp(string(popt(1)*C.TU/60) + " minutes")
// disp(string(m(popt(1))*C.MU)+" kg")
// disp(fval)
// disp(info)
// disp(C)
// // We plot the command
// t = linspace(0,popt(1),100)
// phi_hist = []
// for ti=t
//     s = sol([ti,popt(2),popt(3),popt(4)])
//     phi = atan(s(5),s(6))
//     phi_hist = [phi_hist phi]
// end
// title("Historique de la commande")
// xlabel("Temps en jours")
// ylabel("Phi en degré")
// plot2d(t*C.TU/86400,phi_hist*180/%pi)









