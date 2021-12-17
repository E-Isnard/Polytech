function t=ElemFinisPk()
  Ne = 6;
  Np = 3*Ne;
  Nddl_P1 = Np;
  Ndde_P1 = 3;
  Nddl_P2=Np+14;
  Ndde_P2 = 6;
  
  
  Nddl = Nddl_P2;
  Ndde = Ndde_P2;
  Nuddl = Nu_P2();
  coor = coord_P2();
  [wg,xig,Ng] = gaussPoints();
  Bf_P1 = xig;
  Bf_P2(1:Ng,1) = xig(1:Ng,1).*(2.0*xig(1:Ng,1) -1);
  Bf_P2(1:Ng,2) = xig(1:Ng,2).*(2.0*xig(1:Ng,2) -1);
  Bf_P2(1:Ng,3) = xig(1:Ng,3).*(2.0*xig(1:Ng,3) -1);
  Bf_P2(1:Ng,4) = 4.0*xig(1:Ng,1).*xig(1:Ng,2);
  Bf_P2(1:Ng,5) = 4.0*xig(1:Ng,2).*xig(1:Ng,3);
  Bf_P2(1:Ng,6) = 4.0*xig(1:Ng,3).*xig(1:Ng,1);
  VecB = zeros(Nddl,1);
  MatA = zeros(Nddl,Nddl);
  EdgePoints = [1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6];
  for ie = 1:Ne
    
    is1 = Nuddl(ie,1);
    is2 = Nuddl(ie,2);
    is3 = Nuddl(ie,3);
    
    xe(1,1:2) = coor(is1,1:2);
    xe(2,1:2) = coor(is2,1:2);
    xe(3,1:2) = coor(is3,1:2);
    
    AireT = AireTriangle(xe(1,:),xe(2,:),xe(3,:));
    Grad_P1 = Grad_Lambda_of_P1(xe(1,:),xe(2,:),xe(3,:));
    for g = 1:Ng
      Grad_P2(1,:) = (4.0*Bf_P1(g,1)-1.0)*Grad_P1(1,:);
      Grad_P2(2,:) = (4.0*Bf_P1(g,2)-1.0)*Grad_P1(2,:);
      Grad_P2(3,:) = (4.0*Bf_P1(g,3)-1.0)*Grad_P1(3,:);
      Grad_P2(4,:) = 4.0*(Bf_P1(g,1)*Grad_P1(2,:)+Bf_P1(g,2)*Grad_P1(1,:));
      Grad_P2(5,:) = 4.0*(Bf_P1(g,2)*Grad_P1(3,:)+Bf_P1(g,3)*Grad_P1(2,:));
      Grad_P2(6,:) = 4.0*(Bf_P1(g,3)*Grad_P1(1,:)+Bf_P1(g,1)*Grad_P1(3,:));
      xg = sum(xe(:,1).*Bf_P1(g,:)');
      yg = sum(xe(:,2).*Bf_P1(g,:)');
    
      for il=1:Ndde
        is = Nuddl(ie,il);
        Bi = Bf_P2(g,il);
        Gi(1:2) = Grad_P2(il,1:2)';
        bei = TheRhs(xg,yg)*Bi;
        VecB(is) = VecB(is)+2.0*AireT*wg(g)*bei;
        for jl=1:Ndde
          js = Nuddl(ie,jl);
          Bj = Bf_P2(g,jl);
          Gj(1:2) = Grad_P2(jl,1:2);
          aeij = sum(Gi.*Gj);
          MatA(is,js) = MatA(is,js)+2*AireT*wg(g)*aeij;
        endfor
      endfor
    endfor
    
    
  endfor

for iedge=EdgePoints
  MatA(iedge,:) = 0;
  MatA(iedge,iedge)=1;
  VecB(iedge) = 0;
endfor
sA = sparse(MatA);
sb = sparse(VecB);
var_P2 = bicg(sA,sb);
u_ext = sin(pi*coor(:,1)).*sin(2*pi*coor(:,2));
norm(var_P2-u_ext)

endfunction

function G=Grad_Basis_of_P2(xc,xi,xj,xk)
  Lambda_xc = Lambda_of_P1(xc,xi,xj,xk);
  G_P1 = Grad_Lambda_of_P1(xi,xj,xk);
  
  G(1,:) = 4.0*Lambda_xc(1)*G_P1(1,:)-G_P1(1,:);
  G(2,:) = 4.0*Lambda_xc(2)*G_P1(2,:)-G_P1(2,:);
  G(3,:) = 4.0*Lambda_xc(3)*G_P1(3,:)-G_P1(3,:);
  
  G(4,:) = 4.0*(Lambda_xc(1)*G_P1(2,:)+Lambda_xc(2)*G_P1(1,:));
  G(5,:) = 4.0*(Lambda_xc(2)*G_P1(3,:)+Lambda_xc(3)*G_P1(2,:));
  G(4,:) = 4.0*(Lambda_xc(3)*G_P1(1,:)+Lambda_xc(1)*G_P1(3,:));
  
endfunction

function aire=AireTriangle(xi,xj,xk)
  xij = xj(1)-xi(1);
  yij = xj(2)-xi(2);
  xjk = xk(1)-xj(2);
  yjk = xk(2)-xj(2);
  aire = 1/2*abs(xij*yjk-yij*xjk);
endfunction

function out=Lambda_of_P1(xc,xi,xj,xk)
  aireT = AireTriangle(xi,xj,xk);
  out(1) = AireTriangle(xc,xj,xk)/aireT;
  out(2) = AireTriangle(xi,xc,xk)/aireT;
  out(3) = AireTriangle(xi,xj,xc)/aireT;
endfunction

function gradBf = Grad_Lambda_of_P1(xi,xj,xk)
  
  xij = xj(1)-xi(1);
  yij = xj(2)-xi(2);
  xjk = xk(1)-xj(2);
  yjk = xk(2)-xj(2);
  
  Jac = xij*yjk-yij*xjk;
  
  Gi(1) = -yjk/Jac;
  Gi(2) = xjk/Jac;
  
  Gj(1) = (yjk+yij)/Jac;
  Gj(2) = -(xjk+xij)/Jac;
  
  Gk(1) = -yij/Jac;
  Gk(2) = xij/Jac;
  
  gradBf(1,:) = Gi;
  gradBf(2,:) = Gj;
  gradBf(3,:) = Gk;
  
endfunction

function P2_Bf=Basis_of_P2(xc,xi,xj,xk)
  Lambda_xc = Lambda_of_P1(xc,xi,xj,xk);
  P2_Bf(1) = 2.0*Lambda_xc(1)*Lambda_xc(1)-Lambda_xc(1);
  P2_Bf(2) = 2.0*Lambda_xc(2)*Lambda_xc(2)-Lambda_xc(2);
  P2_Bf(3) = 2.0*Lambda_xc(3)*Lambda_xc(3)-Lambda_xc(3);
  
  P2_Bf(4) = 4.0*Lambda_xc(1)*Lambda_xc(2);
  P2_Bf(5) = 4.0*Lambda_xc(2)*Lambda_xc(3);
  P2_Bf(6) = 4.0*Lambda_xc(3)*Lambda_xc(1);
  
endfunction

function tab=Nu_P2()
  tab(1,:) = [1 11 13 6 12 7];
  tab(2,:) = [13 3 1 8 2 7];
  tab(3,:) = [3 13 15 8 14 9];
  tab(4,:) = [15 5 3 10 4 9];
  tab(5,:) = [11 21 23 16 22 17];
  tab(6,:) = [23 13 11 18 12 17];
  tab(7,:) = [13 23 25 18 24 19];
  tab(8,:) = [25 15 13 20 14 19];
endfunction


function tab=coord_P2()
  tab(1,:) = [0.00 0.00];
  tab(2,:) = [0.00 0.25];
  tab(3,:) = [0.00 0.50]; 
  tab(4,:) = [0.00 0.75];
  tab(5,:) = [0.00 1.00]; 
  tab(6,:) = [0.50 0.00];
  tab(7,:) = [0.50 0.25];
  tab(8,:) = [0.50 0.50];
  tab(9,:) = [0.50 0.75];
  tab(10,:) = [0.50 1.00]; 
  tab(11,:) = [1.00 0.00];
  tab(12,:) = [1.00 0.25];
  tab(13,:) = [1.00 0.50];
  tab(14,:) = [1.00 0.75];
  tab(15,:) = [1.00 1.00];
  tab(16,:) = [1.50 0.00];
  tab(17,:) = [1.50 0.25];
  tab(18,:) = [1.50 0.50];
  tab(19,:) = [1.50 0.75];
  tab(20,:) = [1.50 1.00];
  tab(21,:) = [2.00 0.00];
  tab(22,:) = [2.00 0.25];
  tab(23,:) = [2.00 0.50];
  tab(24,:) = [2.00 0.75];
  tab(25,:) = [2.00 1.00];
endfunction

function [wg,xig,Ng] = gaussPoints()
    Ng=3;
    wg(1:3) = 25/96;
    wg(4) = -27/96;
    xig(:,1) = [1 4 1]/6.0;
    xig(:,2) = [1 1 4]/6.0;
    xig(:,3) = 1.0-xig(:,1)-xig(:,2);

endfunction

function f=TheRhs(xg,yg)
  f = 5*pi^2*sin(pi*xg)*sin(2*pi*yg);
endfunction

function u=u_ext(x,y)
  u = sin(pi*x)*sin(2*pi*y)
endfunction



