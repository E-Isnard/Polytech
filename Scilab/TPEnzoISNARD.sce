disp("Exercice 1")

function A=Matsymetrique(n)
    A=rand(n,n)
    A=A+A'
    
endfunction

function A=MatS(n)
    A=Matsymetrique(n)
    [D,P] = bdiag(A);D=abs(D)
    //disp(P*P') Calcul pour voir si P est unitaire
    D=D+norm(D)*eye(D);
    A=P*D*inv(P)
    
endfunction

//1
A=MatS(2)
B=MatS(3)
C=MatS(4)

disp("Matrices obtenues pour n=2,3 et 4")

disp(A)
disp(B)
disp(C)

disp("Déterminants des matrices MatS pour n=2,3 et 4")
disp(det(A))
disp(det(B))
disp(det(C))
disp("_______________________________________________")

//2


A=MatS(10)

disp("Déterminants des Ak")
for k=1:10
    Ak=A(1:k,1:k)
    disp(det(Ak))
end
//On voit que det(Ak)>0 pour tout k


//3
disp("_______________________________________________")

disp("Spectre de A")
disp(spec(A))
disp("_______________________________________________")
disp("Spectre des Ak")

for k=1:10
    Ak=A(1:k,1:k)
    disp(spec(Ak))
end

//On voit que Spec(Ak) n'est pas inclu dans spec(A)
// Si on prend A symétrique de dimension 2 A s'écrit [a b ; b d]
// On voit que la sous-matrice de A est (a) et elle a donc comme valeur propre a
// Le déterminant caractéristique de A s'écrit χ=X²-tr(A)X+det(A) = X²-(a+d)X+(ad-b²)
// En évaluant χ en d on a  χ(a) = a²-a² - ad² + ad -b² = -b²
// En général meme pour une matrice symétrique définie positive b=/=0 donc d n'est pas valeur propre de A et donc spec(Ak) n'est pas inclu en général dans spec(A)
  

//4
//MatS renvoie des matrices symétriques défnies positives car comme la matrice A dans la fonction est symétrique donc elle est diagonalisable et ses vecteurs propres 
//sont orthogonaux.bdiag renvoie donc comme matrice D une matrice diagonale. Après test j'ai pu voir que scilab norme les vecteurs de P donc P est unitaire,ie P'=P^-1
// La manip dans MatS permet de rendre tous les coefficients de D positifs donc la matrice n'a que des valeurs propres positifs donc elle est définie positive.
// De plus comme P est unitaire on a A = PDP' <=> A'=(PDP')'=P''D'P'=PDP'=A donc A est symétrique

disp("Exercice 2")

A=rand(5,5)
//1
disp("Spectre de A")
S = spec(A)
disp(S)

//2
//a
gammav = [] //vecteur contenant les differents gammai
for i=1:5
    gammai = sum(abs(A(i,[1:i-1 i+1:5])))
    gammav = [gammav gammai]
    
end
disp("Gammav")
disp(gammav)
//b
disp("Test pour voir si pour tout lambda on a un i tel que lambda∈Di")
for lambda=S
    
    for i=1:5
        
        disp(abs(lambda-A(i,i))<gammav(i))
    end
end

//3

//a

function A=MatDiagDom(n)
    A=rand(n,n)
    for i=1:n
        gammai = sum(abs(A(i,[1:i-1 i+1:n])))
        A(i,i) = abs(A(i,i))+2*gammai
    end
endfunction

A=MatDiagDom(4)
disp("Matrice dominante de dimension 4")
disp(A)
disp("_______________________________________________")
//b


disp("Déterminants des matrices dominantes pour n=2,...,6")

for n=2:6
    //On calcule différentes matrices dominantes
    ADom = MatDiagDom(n)
    disp(det(ADom))
end
//Tous les déterminants des matrices sont positifs

//Exercice 3


function z=f(x,y)
    z=x.^2+y.^2
endfunction

x=linspace(-1,1,20)
y=linspace(-1,1,20)

z = feval(x,y,f)
title("Ensemble des points tels que z²=x²+y²")
plot3d(x,y,z)









