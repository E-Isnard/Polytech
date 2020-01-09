function y=f(x)
    y=x.^3;
endfunction

function y=df(x)
    y=3.*x.*x
endfunction

function [x2,y]=dfnum1(x)
    for i=2:length(x)-1
        y(i-1)=(f(x(i+1))-f(x(i)))/((x(i+1)-x(i)))
        x2(i-1)=x(i)
    end  
endfunction


function [x2,y]=dfnum(x)
    for i=2:length(x)-1
        y(i-1)=(f(x(i+1))-f(x(i-1)))/((x(i+1)-x(i-1)))
        x2(i-1)=x(i)
    end  
endfunction



for i=1:4
x=[1:10^(-i):4];
y=df(x);
[x2,ynum]=dfnum(x);//Dérivée centrée
[x1,ynum1]=dfnum1(x)//Dérivée décentrée
y=y(2:$-1);;
x=x(2:$-1);
plot(x,[y; ynum'],[-3,1]);
//plot(x,ynum);
xtitle('Erreur en fonction de h')
xlabel('x')
ylabel('df/dx')
e(i)=max(abs(y'-ynum));
e1(i)=max(abs(y'-ynum1));
h(i)=10^(-i);
end
scf(1)
plot(log(h),log(e))
xtitle('Erreur en fonction de h')
xlabel('log(h)')
ylabel('log(e)')
disp("Ordre de convergence de la méthode des points milieux:")
o=(log(e(2))-log(e(3)))/(log(h(2))-log(h(3)))
disp(o)
plot(log(h),log(e1))


disp("Ordre de convergence de la méthode des points décentrée à droite:")
o=(log(e1(2))-log(e1(3)))/(log(h(2))-log(h(3)))
disp(o)


for i=1:7
h2(i)=10^(-i);
x=[5-h2(i) 5. 5+h2(i)];
[x2,ynum2]=dfnum(x);
e2(i)=abs(df(x2)-ynum2)
end
scf(2)
plot(log(h2),log(e2))
xtitle('Erreur sur la dérivée numérique en 1 point')
xtitle('Erreur en fonction de h')
xlabel('log(h)')
ylabel('log(e)')
