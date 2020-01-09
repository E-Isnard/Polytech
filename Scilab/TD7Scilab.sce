function [x]=remontee(A,b) 
    if(size(A,1)==size(A,2) & det(A)~=0 & triu(A)==A) then
    n=size(A,1); //la matrice est carré triangulaire
    x(n)=b(n)/A(n,n);
    for i=n-1:-1:1 
        p=0
        for j=n:-1:i+1 
             p = p+A(i,j)*x(j)
        end
        x(i)=(b(i)-p)/A(i,i);
    end
else
    error("Matrice incorrecte")
    end
endfunction


function [x]=descente(A,b) 
    if(size(A,1)==size(A,2) & det(A)~=0 & tril(A)==A) then
    n=size(A,1); //la matrice est carré triangulaire
    x(1)=b(1)/A(1,1);
    for i=2:n 
        p=0
        for j=1:i-1
             p = p+A(i,j)*x(j)
        end
        x(i)=(b(i)-p)/A(i,i);
    end
else
    error("Matrice incorrecte")
    end
endfunction

function x=kramer(A,b)
    
    if(size(A,1)==size(A,2) & det(A)~=0) then
        n = size(A,1)
        d = det(A)
        for i=1:n
            B=A
            B(:,i)=b
            
            x(i)=det(B)/d
        end
     else
         error("Matrice inccorecte")
     end
        
endfunction
/*
A=rand(1000,1000)
b=ones(1000,1)
timer()
x=A\b
//disp(x)
disp("Temps pour exécuter A\b")
disp(timer())
timer()
x = kramer(A,b)
disp("Temps pour kramer")
disp(timer())
*/

// Exercice 3

//1

/*
Quand on est au coeff aij on a parcour j colonne et sum(n=1 i-1) coeff donc on a parcouru j+ i(i-1)/2 coeffs donc k = j+ i(i-1)/2
*/

function V=StockI(A)
    n=size(A,1)
    m = n*(n+1)/2
    V = zeros(1,m)
    for j=1:n
        v = A(j,1:j)
        k = j*(j-1)/2+1
        V(k:k+j-1)=v
        //disp(v)
    end
endfunction


function M=VtoM(V)
    k = length(V)
    n = -1/2+sqrt(1/4+2*k)
    M = zeros(n,n)
    for i=1:n
        l = i*(i-1)/2+1
        M(i,1:i) = V(l:l+i-1)
        //disp(M(i,1:i))
        //disp(V(l:l+i-1))
        
    end
endfunction
/*
V = [1 1 1 1 1 1]
A=VtoM(V)
disp(A)
*/
function [x]=descenteV(V,b) 
    l = length(V)
    n = -1/2 + sqrt(1/4+2*l)
    x(1)=b(1)/V(1,1);
    for i=2:n 
        p=0
        for j=1:i-1
             k = j+i*(i-1)/2
             p = p+V(k)*x(j)
        end
        k = (i)*(i+1)/2
        x(i)=(b(i)-p)/V(k);
    end

endfunction
/*
A = [1 0; 1 1]
b=[1212 121]'
x1 = descente(A,b)
V = StockI(A)
x2 = descenteV(V,b)
disp(x1)
disp(x2)
*/


function [L,U]=FactLU(A)
    n= size(A,1)
    for k=1:n-1
        for i=k+1:n
            for j=k+1:n
                A(i,j)=A(i,j)-(A(i,k)/A(k,k)*A(k,j))
            end
        end
        for i=k+1:n
            A(i,k)=A(i,k)/A(k,k)
        end
        
    end
    
    U=triu(A)
    A=A-diag(diag(A))
    L=tril(A)+diag(ones(1,n))
endfunction


/*
A=[1 2 ; 1 2]

[L,U] = FactLU(A)
disp(L)
disp(U)
disp(L*U)
*/

function y=hilbertPerso(m,n)
    for i=1:1:n
        for j=1:1:n
            M(i,j)=1/(i+j-1)
        end
    end
    y=M
endfunction

e=0.1
A=[e 1 1 ; 1 -1 1; 1 0 1 ]
b=[2 0 1]'
[l,u,p] = lu(A)
[L,U]=FactLU(A)
x = remontee(U,descente(L,b))
x2 = remontee(u,descente(l,p*b))

disp(A)
disp(L)
disp(U)
[l u]=FactLU(p*A)
disp(l)
disp(u)
disp(l*u)
disp(L*U)
disp(x) // un peu faux
disp(x2)







