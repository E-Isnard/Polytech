clear
function fb = TheRhs(s,mu,x,y)    
    ax=1.0
    ay=2.0
    Coef = s + mu*(ax*ax + ay*ay)*%pi*%pi
    fb   = Coef*sin(ax*%pi.*x).*sin(ay*%pi.*y);
endfunction

function Exact = Exact_Sol( x,y)      
    ax=1.0
    ay=2.0
    Exact  = sin(%pi.*x).*sin(2.0*%pi.*y)
endfunction


function GradBf = Grad_Lambda_of_P1(Xe)
   
      Xi(:) = Xe(1,:)
      Xj(:) = Xe(2,:)
      Xk(:) = Xe(3,:)
      
      xij = Xj(1) - Xi(1)
      yij = Xj(2) - Xi(2)
      
      xjk = Xk(1) - Xj(1)
      yjk = Xk(2) - Xj(2)
      
      aire =  xij * yjk - yij * xjk 
    
    Gi(1) = - yjk/aire
    Gi(2) =   xjk/aire
    
    Gj(1) =   (yjk + yij )/aire
    Gj(2) = - (xjk + xij )/aire
    
    Gk(1) = - yij/aire
    Gk(2) =   xij/aire 
    
    GradBf(1,:) = Gi(:)
    GradBf(2,:) = Gj(:)
    GradBf(3,:) = Gk(:)
    
endfunction


Lx    = 2.0
Ly    = 1.0

Nmesh = 5
h_vec = []
for msize =1:Nmesh

Nx_P1 = 6*msize
Ny_P1 = 3*msize

Nx    = 2*(Nx_P1-1) + 1
Ny    = 2*(Ny_P1-1) + 1

dx    = Lx/(Nx-1)
dy    = Ly/(Ny-1)
// Vecteurs pour la convergence
h_vec = [h_vec min(dx,dy)]

// ----------------------------------------------------------
is    = 0
ib    = 0

for ix = 1:Nx
    xi = (ix -1)*dx
    
    for iy =1:Ny
        yj = (iy-1)*dy
        is = is +1
        
        // Coordonnées des noeuds  P2

        Coor_P2(is, 1) = xi
        Coor_P2(is, 2) = yj
        
        //  Identification des ddls sur le bord

        if( ix == 1) then
            ib        = ib +1
            Nu_bord(ib) = is
        elseif (ix==Nx) then
            ib        = ib +1
            Nu_bord(ib) = is
        elseif (iy==1) then
            ib        = ib +1
            Nu_bord(ib) = is
        elseif (iy==Ny) then
            ib        = ib +1
            Nu_bord(ib) = is
        end 
        
    end
end

Nddl_P2   = is
Nddl_bord = ib

//   Noeuds géométriques et de calcul

ie =0
for ix =1: Nx_P1-1
    for iy =1:Ny_P1-1
        // élément en bas à droite
        // --------------------------
        ie  = ie +1
        is1 = 2*(ix-1)*Ny + (iy-1)*2 + 1
        is2 = is1 + 2*Ny
        is3 = is2 + 2
        is4 = is1 + Ny
        is5 = is2 + 1
        is6 = is4 + 1
        Nu_P2(ie,1:6) = [is1, is2, is3, is4, is5, is6]
        
        // élément en haut à gauche
        // --------------------------
        ie  = ie +1
        is1 = 2*ix*Ny + iy*2 + 1 
        is2 = is1 - 2*Ny
        is3 = is2 - 2
        is4 = is1 - Ny
        is5 = is2 - 1
        is6 = is4 - 1
        Nu_P2(ie,1:6) = [is1, is2, is3, is4, is5, is6]
    end
end
Ne = ie

Nddl_P1 = Nx_P1*Ny_P1;
Ndde_P1 = 3;

Nddl_P2 = Nx*Ny  ;
Ndde_P2 = 6      ;

Nddl  = Nddl_P2;
Ndde  = Ndde_P2;
Nuddl = Nu_P2 ; 

Coor = Coor_P2;


Scoef  = 0.0
MuCoef = 1.0

// Points de Gauss
 Ng                = 6;
 P1 = 0.11169079483905;
 P2 = 0.0549758718227661;
 aa = 0.445948490915965;
 bb = 0.091576213509771;
 W_g(1:3)             = P2; 
 W_g(4:6)             = P1;

 X_g(1:Ng, 1)         = [bb, 1-2*bb, bb, aa, aa, 1-2*aa];
 X_g(1:Ng, 2)         = [bb, bb, 1-2*bb, 1-2*aa, aa, aa];
//------------------------------------------------------------

X_g(1:Ng, 3)         = 1.0 - X_g(1:Ng, 1) - X_g(1:Ng, 2);

Bf_P1(1:Ng, 1)        = X_g(1:Ng, 1);
Bf_P1(1:Ng, 2)        = X_g(1:Ng, 2);
Bf_P1(1:Ng, 3)        = X_g(1:Ng, 3);


Bf_P2(1:Ng, 1)        = X_g(1:Ng, 1).*( 2.0*X_g(1:Ng, 1) - 1.0 );
Bf_P2(1:Ng, 2)        = X_g(1:Ng, 2).*( 2.0*X_g(1:Ng, 2) - 1.0 );
Bf_P2(1:Ng, 3)        = X_g(1:Ng, 3).*( 2.0*X_g(1:Ng, 3) - 1.0 );

Bf_P2(1:Ng, 4)        = 4.0*X_g(1:Ng, 1).* X_g(1:Ng, 2);
Bf_P2(1:Ng, 5)        = 4.0*X_g(1:Ng, 2).* X_g(1:Ng, 3);
Bf_P2(1:Ng, 6)        = 4.0*X_g(1:Ng, 3).* X_g(1:Ng, 1);


MatA(1:Nddl, 1:Nddl) = 0.0 ;
VecB(1:Nddl)         = 0.0 ;

for ie = 1:Ne
    
    is1 = Nuddl(ie, 1);
    is2 = Nuddl(ie, 2);
    is3 = Nuddl(ie, 3);
    
    Xe(1,1:2)  = Coor(is1,1:2)
    Xe(2,1:2)  = Coor(is2,1:2)
    Xe(3,1:2)  = Coor(is3,1:2)
    
    Grad_P1 = Grad_Lambda_of_P1(Xe)   
     
    
    AireT = 0.5*(  (Xe(2,1) - Xe(1,1))*(Xe(3,2) - Xe(1,2)) ...
                 - (Xe(2,2) - Xe(1,2))*(Xe(3,1) - Xe(1,1)) )

     AireT = abs(AireT);
      
    for g=1:Ng
        

     Grad_P2(1, : ) = ( 4*Bf_P1(g,1) - 1.0 )*Grad_P1(1, : );
     Grad_P2(2, : ) = ( 4*Bf_P1(g,2) - 1.0 )*Grad_P1(2, : );
     Grad_P2(3, : ) = ( 4*Bf_P1(g,3) - 1.0 )*Grad_P1(3, : );
     
     Grad_P2(4, : ) = 4.0*(  Bf_P1(g,1)*Grad_P1(2,:) ...
                           + Bf_P1(g,2)*Grad_P1(1,:));
                         
     Grad_P2(5, : ) = 4.0*(Bf_P1(g,2)*Grad_P1(3,:) ...
                         + Bf_P1(g,3)*Grad_P1(2,:));
                         
     Grad_P2(6, : ) = 4.0*(Bf_P1(g,3)*Grad_P1(1,:) ...
                         + Bf_P1(g,1)*Grad_P1(3,:));
        
        // Points physiques
        xg = sum( Xe(:,1).*Bf_P1(g,:)' )
        yg = sum( Xe(:,2).*Bf_P1(g,:)' )
        

        for il=1:Ndde
            // numérotation locale (il)
            // vers numérotation globale (is)

            is = Nuddl(ie,il)
            
            Bi      = Bf_P2(g,il)      
            Gi(1:2) = Grad_P2(il,1:2);
            
            bei = TheRhs(Scoef, MuCoef, xg,yg)*Bi
            

            VecB(is) = VecB(is) + 2.0*AireT*W_g(g)*bei
            
            for jl = 1:Ndde

                 js = Nuddl(ie,jl)
                 
                 Bj      = Bf_P2(g,jl)
                 Gj(1:2) = Grad_P2(jl,1:2);
                
                aeij = Scoef*Bi*Bj +  MuCoef*sum(Gi.*Gj)
                
                
                // Assemblage de la matrice globale
                
                MatA(is,js) = MatA(is,js) + 2.0*AireT*W_g(g)*aeij
                
            end
            
        end
         
    end
end 

// conditions aux limites
for ib = 1:Nddl_bord;
    is               = Nu_bord(ib);   
    MatA(is,1:Nddl)  = 0.0;
    MatA(is,is)      = 1.0;
    
    xi        = Coor(is, 1)
    yj        = Coor(is, 2)
    VecB(is)  = Exact_Sol(xi, yj); 
end

        Sparse_MatA = sparse(MatA); 
        Var_P2      = umfpack(Sparse_MatA,"\",VecB);

        for is = 1:Nddl
            xi = Coor(is, 1)
            yj = Coor(is, 2)
            Var_Exact(is) = Exact_Sol( xi,yj)
         end


   ErrorS_L2(msize) = sqrt( sum( (Var_P2 -Var_Exact).^2 ) )
   ErrorS_Li(msize) = max( abs(Var_P2 - Var_Exact) )

end

[a,b,sig] = reglin(log(h_vec),log(ErrorS_L2)')
disp("L ordre de convergence en norme L2 en P2 est ",a)
[a,b,sig] = reglin(log(h_vec),log(ErrorS_Li)')
disp("L ordre de convergence en norme L_inf en P2 est ",a)

plot(log(h_vec),log(ErrorS_Li)')
