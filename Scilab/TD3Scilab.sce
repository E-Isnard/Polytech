//Exercice 1

function y=fib(n)
    u1=1
    u2=1
    for i=1:1:n
        sumNum=u1+u2
        u1=u2
        u2=sumNum
    end
    y=sumNum
endfunction

//Exercice 2


function y=fibTh(th)
    i=3
    while(fib(i)<th) do
        i=i+1
    end
    y=i
endfunction

//Exercice 3

function y=sumBoucle(n)
    s=0
    for i=1:1:n
        s=s+i
    end
    y=s
endfunction

function y=sumVect(n)
    u=1:1:n
    y=sum(u)
endfunction

//Exercice 4

function y=squareM(n)
    m=zeros(n,n)
    u=1:1:n
    for i=1:1:n
        m(:,i)=u
        u=u+n
    end
    y=m
    
    
endfunction

//Exercice 5

function y=toeplitzN(n)
    m=diag(ones(n,1)*n)
    for i=1:1:n-1
        m=m+diag(ones(n-i,1)*n-i,i)+diag(ones(n-i,1)*n-i,-i)
    end
    y=m
endfunction

//Exercice 6

function y=Z(n)
    u=ones(n,1)
    m=zeros(n,n)
    m(1,:)=u
    m(n,:)=u
    for i=0:1:n-1
        m(1+i,n-i)=1
    end
    y=m
    
endfunction

//Exercice 7

function y=reverse(u)
    v=[]
    n=size(u,"c")
    for i=1:1:n
        v(i)=u(n+1-i)
    end
    y=v
endfunction

//Exercice 8

function y=X(n)
    u=ones(n,1)
    m=diag(u)
    for i=0:1:n-1
        m(1+i,n-i)=1
    end
    y=m
    
endfunction







timer();
//disp(fibTh(1E34))
//disp(sumVect(1000000))
//disp(sumVect(1E20))
//disp(squareM(10))
//disp(toeplitzN(8))
//disp(reverse([1,2,3,4]))
disp(X(1000))
disp(timer())
funcprot(0)




