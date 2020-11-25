clear;
clc;
dt=15; % door temperature
ht=200; % heat temperature
ot =-10; % outside temperature
n=100; % n² = number of point in mesh
nt=100;
cfl=30;
x = linspace(-1,1,n);                            
[X,Y] = meshgrid(x,x);                            
G = ((Y<0.8) & (Y<1) & (X>-1) & (X<1) & (Y>-1)) | ((Y>0.8) & (Y<1) & (X >-0.2) & (X<0.2)); % geometry of the room
H = ((Y>-0.8) & (Y<-0.4) & (X<-0.8) & (X>-1)); % heat

k = find(G);                       
G = zeros(size(G));                 
G(k) = (1:length(k))';
A = delsq(G);
door = [];                          
window = [];                        
for i = 2:n-1                       
  for j = 2:n-1                     
    ind = G(i,j);
    if ind~=0
      if G(i,j-1) == 0              
        if ((Y(i,j)>0.2) && (Y(i,j)<0.8))
          window = [window ind];
        else
            A(ind,ind) = A(ind,ind)-1;
        end
      end
      if G(i,j+1) == 0
        A(ind,ind)=A(ind,ind)-1;
      end
      if G(i+1,j) == 0   
        if (X(i,j)>-0.2 && X(i,j)<0.2)
            door = [door ind];
        else
          A(ind,ind) = A(ind,ind)-1;
        end
      end
      if G(i-1,j) == 0
        A(ind,ind)=A(ind,ind)-1;
      end 
    end
  end
end

h = 2/(n-1);
t = h^2*cfl;

b = zeros(length(k),1);
% window = G(end-1,G(end-1,:)>0);     % indices correspondant a la fenetre
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
