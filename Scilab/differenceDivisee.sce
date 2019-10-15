close
close
clear
clf
function [a]=DD(x,y)
    // Calcule le tableau des différences divisées pour le polynome de Lagrange
    // pour les données (x_i,y_i), i=1,2...,N+1
    // Entrées: x liste de N+1 points distincts, y liste de N+1 points
    // Sorties: a vecteur avec les différences divisées
    
    // Initialisations
    N    = length(x);
    N    = N-1;
    a    = zeros(1,N+1);
    a(1) = y(1);
    
    vecteur = y;
    
    // Boucle
    for k = 1:N
        vecteur_courant = (vecteur(2:$)-vecteur(1:$-1))./(x(k+1:$)-x(1:$-k));
        a(k+1)  = vecteur_courant(1);
        vecteur = vecteur_courant;
    end

endfunction

function val=horner2(X,a,t)
    //a=différences divisées
    //X=points d'interpolations
    //t=point où on évalue
    n=size(X)(2)
    b=zeros(1,n)
    b($)=a($)
    for i=n-1:-1:1
        b(i)=a(i)+(t-X(i))*b(i+1)
        
    end
    val = b(1)
    
endfunction

function y=h(x,p)
    y=1./(1+p.*x.*x)
endfunction

//disp(DD([-1,0.5,1.5,2],[exp(-1),exp(0.5),exp(1.5),exp(2)]))
//a=DD([-1,0.5,1.5,2],[exp(-1),exp(0.5),exp(1.5),exp(2)]);
//X=[-1,0.5,1.5,2]
//t=2
//disp(horder(X,a(1,:),t))
N=60
p=30
T=linspace(-1,1,300)
img = h(T,p)

X= cos( %pi*(2*(0:N-1)+1)/(2*(N-1)+2)); //Points de Tchebychev


Y=h(X,p)
dfd = DD(X,Y)
P=zeros(1,size(T)(2))

for i=1:1:size(T)(2)
    P(i)=horner2(X,dfd(1,:),T(i))
end

e=abs(img-P)
plot2d(T,img)
//scf(1)
plot2d(T,P,style=5)
scf(1)
plot2d('nl',T,e)


