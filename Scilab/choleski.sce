function L=Choleski(A)
    n = size(A,1)
    L=zeros(n,n)
    L(1,1)=sqrt(A(1,1))
    for j=2:n
        L(j,1)=A(1,j)/L(1,1)
    end
    
    for i=2:n
        L(i,i)=sqrt(A(i,i)-sum(L(i,:).^2))
        
        for j=i+1:n
            L(j,i)=(A(i,j)-sum(L(:,i).*L(j,:)'))/L(i,i)
        end
    end
    
endfunction


A=[16 -8 4; -8 29 12 ; -4 12 41 ]
L = Choleski(A)
disp(L)
disp(A)
disp(L*L')
