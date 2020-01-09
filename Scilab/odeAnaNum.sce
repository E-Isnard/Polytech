close
close
close
close
close
function y=euler(f,t,y0)
    y=zeros(1,length(t))
    y(1)=y0
    for i=2:length(t)
        y(i)=y(i-1)+(t(i)-t(i-1))*f(t(i-1),y(i-1))
    end
endfunction

function y=rungeKutta2(f,t,y0)
    y=zeros(1,length(t))
    y(1)=y0
    for i=2:length(t)
        k1=(t(i)-t(i-1))*f(t(i-1),y(i-1))
        k2=(t(i)-t(i-1))*f(t(i),y(i-1)+k1)
        y(i)=y(i-1)+1/2*(k1+k2)
    end
endfunction

function y=adamsBashford(f,t,y0)
    y=zeros(1,length(t))
    y(1)=y0
    y(2)=y0+(t(2)-t(1))*f(t(1),y0)
    for i=3:length(t)
        y(i)=y(i-2)+2*(t(i)-t(i-1))*f(t(i-1),y(i-1))
    end
    
endfunction

function  ev=errorMethode(nv,methode,z,f,y0)
    ev=[]
    for n=nv
        t=linspace(0,1,n)
        yExact=z(t)
        ynum = methode(f,t,y0)
        
        e = norm(ynum-yExact,%inf)
        ev = [ev e]
        
    end


endfunction




n=320
t=linspace(0,1,n)

alpha=2
b=2
g=2
y0=2

deff('z=f1(t,y)','z=alpha*y')
deff('z=f2(t,y)','z=-b*y.^2')
deff('z=f3(t,y)','z=g*%pi*sqrt(1-y.^2)')
deff('z=z1(t)','z=exp(alpha*t).*y0')

yEuler1 = euler(f1,t,y0)
yRunge1 = rungeKutta2(f1,t,y0)
yAdams1 = adamsBashford(f1,t,y0)
yExact1 = exp(alpha*t)*y0


plot2d(t,yEuler1,style=1)
plot2d(t,yRunge1,style=2)
plot2d(t,yAdams1,style=3)
plot2d(t,yExact1,style=4)
title(["Solutions de ","$\dot{y}=\alpha y$"])
legends(['Euler';'Runge';'Adams';'Solution Excacte'],[1,2 3,4],opt="ur")


scf(1)

yEuler2 = euler(f2,t,y0)
yRunge2 = rungeKutta2(f2,t,y0)
yAdams2 = adamsBashford(f2,t,y0)
yExact2 = y0./(y0.*b.*t+1)

plot2d(t,yEuler2,style=1)
plot2d(t,yRunge2,style=2)
plot2d(t,yAdams2,style=3)
plot2d(t,yExact2,style=4)
title(["Solutions de ","$\dot{y}=-\beta y^2$"])
legends(['Euler';'Runge';'Adams';'Solution Excacte'],[1,2 3,4],opt="ur")

scf(2)
yEuler3 = euler(f3,t,y0)
yRunge3 = rungeKutta2(f3,t,y0)
yAdams3 = adamsBashford(f3,t,y0)
yExact3 = sin(asin(y0)+g*%pi.*t)

plot2d(t,yEuler3,style=1)
plot2d(t,yRunge3,style=2)
plot2d(t,yAdams3,style=3)
plot2d(t,yExact3,style=4)
title(["Solutions de ","$\dot{y}=\gamma \pi \sqrt{1-y^2}$"])
legends(['Euler';'Runge';'Adams';'Solution Excacte'],[1,2 3,4],opt="ur")

i=1:6
nv = 5*2^i

evEuler1 = errorMethode(nv,euler,z1,f1,y0)
evKutta1 = errorMethode(nv,rungeKutta2,z1,f1,y0)
evAdams1 = errorMethode(nv,adamsBashford,z1,f1,y0)

scf(3)
plot2d(nv,evEuler1,style=1,logflag="ll")
plot2d(nv,evKutta1,style=2,logflag="ll")
plot2d(nv,evAdams1,style=3,logflag="ll")
title("Erreurs pour la fonction 1")
legends(['Euler';'Runge';'Adams'],[1,2 3],opt="ur")

ordreEuler = ( log(evEuler1(4))-log(evEuler1(3)) )/( log(nv(4))-log(nv(3)) )
disp("ordre d Euler")
disp(ordreEuler)
ordreKutta = ( log(evKutta1(4))-log(evKutta1(3)) )/( log(nv(4))-log(nv(3)) )
disp("ordre de Kutta")
disp(ordreKutta)
ordreAdams = ( log(evAdams1(4))-log(evAdams1(3)) )/( log(nv(4))-log(nv(3)) )
disp("ordre d Adams")
disp(ordreAdams)












