
ot=10;
dt=10;
ht=100;
n=100;
nt=100;
cfl=30;
  
x = linspace(-1,1,n);                            
[X,Y] = meshgrid(x,x);                             % grille bi-dimensionnelle 
G = ((X>-1) & (X<0) & (Y>-1) & (Y<0.95)) | ...  % geometrie de la chambre
   ((X>0) & (X<1) & (Y>0) & (Y<0.95)) | ... 
   ((X>0.3) & (X<0.7) & (Y>-0.2) & (Y<0)) | ...
   ((X>-0.7) & (X<-0.2) & (Y>0.95) & (Y<1)) | ...
   ((X>0.2) & (X<0.7) & (Y>0.95) & (Y<1));
%H=[];  %pas de chauffage
H = ((X>-1) & (X<-0.8) & (Y>-0.1) & (Y<0.3));       % position du chauffage
k = find(G);                        % les indices des G non nuls
G = zeros(size(G));                 % conversion logique - reel  
G(k) = (1:length(k))';              % suite d'indices de la matrice A  
A = delsq(G);                       % Laplacien a l'interieur du domaine G
door = [];                          % indices correspondant a la porte
for i = 2:n-1                       % ajout de CL Neumann
  for j = 2:n-1                     % pour les murs isolants
    ind = G(i,j);
    if ind~=0
      if G(i,j-1) == 0              % CL Neumann sur le mur gauche
        A(ind,ind) = A(ind,ind)-1;
      end
      if G(i,j+1) == 0              % CL Neumann sur le mur droit
        A(ind,ind) = A(ind,ind)-1;
      end
      if G(i+1,j) == 0 && i<n-1     % On garde la CL Dirichlet pour la fenetre
        A(ind,ind) = A(ind,ind)-1;  % CL Neumann sur le mur du bas 
      end
      if G(i-1,j) == 0
        if (X(i,j)>0.3 && X(i,j)<0.7 && Y(i,j)>-0.2 && Y(i,j)<-0.1 ) 
          door = [door ind];        % On garde la CL Dirichlet pour la porte 
        else
          A(ind,ind) = A(ind,ind)-1; % CL Neumann sur le mur du haut
        end
      end 
    end
  end
end

h = 2/(n-1);
t = h^2*cfl;

b = zeros(length(k),1);
window = G(end-1,G(end-1,:)>0);     % indices correspondant a la fenetre
heat = G(H);                        % indices correspondant au radiateur
b(window) = 1/h^2*ot*t;              % prise en compte des CL Dirichlet
b(heat) = ht*t;                      % et du chauffage
b(door) = 1/h^2*dt*t;

u=b;

B= A*(t/h^2) + eye(length(k));
L=chol(B);
for i=1:nt
%   u = B*u + b;                            % solution du systeme sous forme de vecteur
  w = L'\(u+b);
  u = L\w;
  U = G;
  U(G>0) = full(u(G(G>0)));           % on met la solution dans une matrice
  mesh(X,Y,U);                        % correspondant a la grille 
  axis('ij');                         % on dessine la solution sur la grille
  pause(0.1);
end
