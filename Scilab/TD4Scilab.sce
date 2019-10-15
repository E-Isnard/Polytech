clear
//Exercice 1

//1.

function y=coeffBinRec(n,k)
    if(k==0) then y=1
    elseif(k>n) then y=0
    else
        y=n/k*coeffBin(n-1,k-1)
    end
    
    
    
endfunction

//2.

function y=coeffBinIte(n,k)
    m = zeros(n+1,n+1)
    for i=1:1:n+1
        m(i,1)=1
        for j=2:1:i
            m(i,j) = m(i-1,j)+m(i-1,j-1)
            end
    end
    y=m(n+1,k+1)
      
endfunction


function y=coeffBinM(n)
    m = zeros(n+1,n+1)
    for i=1:1:n+1
        m(i,1)=1
        for j=2:1:i
            m(i,j) = m(i-1,j)+m(i-1,j-1)
            end
    end
    y=m'
      
endfunction

function y=vandermondeDoubleIte(x)
    for i=1:1:size(x,1)
        for j=1:1:size(x,1)
            m(i,j)=x(i).^(size(x,1)-j)
            
        end
    end
    y=m
    
endfunction

function y=vandermondeColIte(x)
    for i=1:1:size(x,1)
        m(:,i)=x.^(size(x,1)-i)
    end
    y=m
endfunction

function y=vandermondeColIte2(x)
    n=size(x,1)
    m=ones(n,n)
    for i=1:1:n-1
        //disp(m)
        m(:,n-i)=x.*m(:,n-i+1)
    end
    y=m
endfunction

//Exercice 3

function m=matSym(n)
    x=rand(n,1)'
    m=x'*x
endfunction

function m=matAntiSym(n)
    x=rand(n,1)'
    t=x'*x
    tu = triu(t,1)
    tl = tril(t,-1)
    m=tu-tl
    
     
endfunction

function y=matRand(m,n,p)
    y=rand(m,n)*2*p-p
    
endfunction

function y=matRand01(m,n)
    y=modulo(floor(rand(m,n)*100),2)
endfunction

function y=hilbertPerso(m,n)
    for i=1:1:n
        for j=1:1:n
            M(i,j)=1/(i+j-1)
        end
    end
    y=M
endfunction

function y=serieCos(x,n)
    s=ones(1,size(x,1))
    i=1
    while(2*i<n)
        s=s+(-1)^i*x.^(2*i)./prod(1:2*i)
        i=i+1
    end
        
    y=s
endfunction



function y=serieSin(x,n)
    s=zeros(1,size(x,1))
    i=0
    while(2*i+1<n)
        s=s+(-1)^i*x.^(2*i+1)./prod(1:2*i+1)
        i=i+1
    end
        
    y=s
endfunction



//disp(coeffBinRec(4,2))
//disp(coeffBinIte(4,2))
x=[0:0.01:10]
disp(serieCos(%pi/3,1000))
disp(serieSin(%pi/3,1000))
y=serieSin(x,100)
z=serieCos(x,100)
//plot2d(x,sin(x),style=3)
plot2d(x,y)
plot2d(x,z,style=2)

funcprot(0)




