close
close
close
//Valeur de l'intégrale de f1
I1=%pi

//Valeur de l'intégrale de f2
I2=2/%pi

//Valeur de l'intégrale de f3

I3=2/(7*%pi)

function I=RectangeDroit(f,x)
    h=x(2)-x(1)
    I=0
    for i=2:length(x)
        I=I+h*f(x(i))
    end
    
endfunction



function I=RectangeGauche(f,x)
    h=x(2)-x(1)
    I=0
    for i=1:length(x)-1
        I=I+h*f(x(i))
    end
    
endfunction



function I=Trapeze(f,x)
    h=x(2)-x(1)
    I=0
    for i=1:length(x)-1
        valMoyenne = (f(x(i))+f(x(i+1)))/2
        I=I+h*valMoyenne
    end
    
endfunction

function I=PointMilieu(f,x)
    h=x(2)-x(1)
    I=0
    for i=1:length(x)-1
        valMilieu = f((x(i)+x(i+1))/2)
        I=I+h*valMilieu
    end
endfunction

function I=Simpson(f,x)
    h=x(2)-x(1)
    I=0
    for i=1:length(x)-1
        m = (x(i)+x(i+1))/2
        I=I+h/6*(f(x(i))+4*f(m)+f(x(i+1)))
    end
endfunction


function y=f1(x)
    y=4./(1+x.^2)
endfunction

function y=f2(x)
    y=cos(%pi/2*x)
endfunction

function y=f3(x)
    y=sin(7*%pi*x)+cos(8*%pi*x)
endfunction

function ev = errorNum(nv,methode,valExact,f)
    ev = []
    for n=nv
        x = linspace(0,1,n)
        Inum = methode(f,x)
        e = abs(valExact - Inum)
        ev = [ev e]
    end
endfunction



n=15
x = linspace(0,1,n)


I1Rd = RectangeDroit(f1,x)
I1Rg = RectangeGauche(f1,x)
I1Ml = PointMilieu(f1,x)
I1Trp = Trapeze(f1,x)
I1S = Simpson(f1,x)
disp("Valeur exacte de I1")
disp(I1)
disp("___________________")
disp("Valeur avec RectangleDroit")
disp(I1Rd) 
disp("___________________")
disp("Valeur avec RectangleGauche")
disp(I1Rg)
disp("___________________")
disp("Valeur avec PointMilieu")
disp(I1Ml)
disp("___________________")
disp("Valeur avec Trapeze")
disp(I1Trp)
disp("___________________")
disp("Valeur avec Simpson")
disp(I1S)


I1Rd2 = RectangeDroit(f2,x)
I1Rg2 = RectangeGauche(f2,x)
I1Ml2 = PointMilieu(f2,x)
I1Trp2 = Trapeze(f2,x)
I1S2 = Simpson(f2,x)
disp("===================")
disp("Valeur exacte de I2")
disp(I2)
disp("___________________")
disp("Valeur avec RectangleDroit")
disp(I1Rd2) 
disp("___________________")
disp("Valeur avec RectangleGauche")
disp(I1Rg2)
disp("___________________")
disp("Valeur avec PointMilieu")
disp(I1Ml2)
disp("___________________")
disp("Valeur avec Trapeze")
disp(I1Trp2)
disp("___________________")
disp("Valeur avec Simpson")
disp(I1S2)

I1Rd3 = RectangeDroit(f3,x)
I1Rg3 = RectangeGauche(f3,x)
I1Ml3 = PointMilieu(f3,x)
I1Trp3 = Trapeze(f3,x)
I1S3 = Simpson(f3,x)
disp("===================")
disp("Valeur exacte de I3")
disp(I3)
disp("___________________")
disp("Valeur avec RectangleDroit")
disp(I1Rd3) 
disp("___________________")
disp("Valeur avec RectangleGauche")
disp(I1Rg3)
disp("___________________")
disp("Valeur avec PointMilieu")
disp(I1Ml3)
disp("___________________")
disp("Valeur avec Trapeze")
disp(I1Trp3)
disp("___________________")
disp("Valeur avec Simpson")
disp(I1S3)

nv = 4:20
evRd = errorNum(nv,RectangeDroit,I1,f1)
evRg = errorNum(nv,RectangeGauche,I1,f1)
evPtMilieu = errorNum(nv,PointMilieu,I1,f1)
evTrapeze = errorNum(nv,Trapeze,I1,f1)
evSimpson = errorNum(nv,Simpson,I1,f1)

plot2d(nv,evRd,logflag="ll")
plot2d(nv,evRg,logflag="ll",style=2)
plot2d(nv,evPtMilieu,logflag="ll",style=3)
plot2d(nv,evTrapeze,logflag="ll",style=4)
plot2d(nv,evSimpson,logflag="ll",style=5)
title(["Erreurs pour" "$\frac{4}{1+x^2}$"])
legends(['RectD';'RectG';'PtMilieu';'Trapeze';'Simpson'],[0,2 3,4,5],opt="ur")

scf(1)

evRd2 = errorNum(nv,RectangeDroit,I2,f2)
evRg2 = errorNum(nv,RectangeGauche,I2,f2)
evPtMilieu2 = errorNum(nv,PointMilieu,I2,f2)
evTrapeze2 = errorNum(nv,Trapeze,I2,f2)
evSimpson2 = errorNum(nv,Simpson,I2,f2)



plot2d(nv,evRd2,logflag="ll")
plot2d(nv,evRg2,logflag="ll",style=2)
plot2d(nv,evPtMilieu2,logflag="ll",style=3)
plot2d(nv,evTrapeze2,logflag="ll",style=4)
plot2d(nv,evSimpson2,logflag="ll",style=5)
title(["Erreurs pour" "$cos(\frac{\pi}{2}x)$"])
legends(['RectD';'RectG';'PtMilieu';'Trapeze';'Simpson'],[0,2 3,4,5],opt="ur")


scf(2)

evRd3 = errorNum(nv,RectangeDroit,I3,f3)
evRg3 = errorNum(nv,RectangeGauche,I3,f3)
evPtMilieu3 = errorNum(nv,PointMilieu,I3,f3)
evTrapeze3 = errorNum(nv,Trapeze,I3,f3)
evSimpson3 = errorNum(nv,Simpson,I3,f3)



plot2d(nv,evRd3,logflag="ll")
plot2d(nv,evRg3,logflag="ll",style=2)
plot2d(nv,evPtMilieu3,logflag="ll",style=3)
plot2d(nv,evTrapeze3,logflag="ll",style=4)
plot2d(nv,evSimpson3,logflag="ll",style=5)
title(["Erreurs pour" "$sin(7\pi x)+cos(8\pi x)$"])
legends(['RectD';'RectG';'PtMilieu';'Trapeze';'Simpson'],[0,2 3,4,5],opt="ur")

x = linspace(0,1,8)








