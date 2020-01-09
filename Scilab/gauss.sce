function T=gauss(A)
    T=A
    n=size(A,1)
    for j=1:n-1
        ip=j
        for i=j:n
            //Recherche de Pivot
            if(abs(T(i,j))>abs(T(ip,j))) then ip=i end
                
        end
        //Echange de ligne
        temp = T(j,:)
        T(j,:)=T(i,:)
        T(i,:)=temp
        for i=j+1:n
            
            T(i,:)=T(i,:)-(T(i,j)/T(j,j))*T(j,:)
        
        end
    end
endfunction

A=[1 2 3 ; 4 3 6; 1 8 9]
T=gauss(A)
disp(A)
disp(T)
