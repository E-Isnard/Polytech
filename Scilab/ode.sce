a=-2
r=1
k=10
h=1/1000
x0=-1
y0=-3
t=0:h:10
x=x0
y=y0

function xpoint=f(x)
    xpoint=a*x
endfunction


function ypoint=g(y)
    ypoint=r*y*(1-y/k)
endfunction

x(1)=x0
y(1)=y0
for i=2:size(t)(2)
    x(i)=x(i-1)+f(x(i-1))*h
    
    
end

for i=2:size(t)(2)
    y(i)=y(i-1)+g(y(i-1))*h
      
end



