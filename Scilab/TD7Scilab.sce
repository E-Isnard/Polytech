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
