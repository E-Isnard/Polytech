/*
//Exercice 1

clf()
x=linspace(-2*%pi,2*%pi);
f1=sin(x);
f2=sin(2*x);
f3=sin(3*x);


title("Les sinus")
xlabel("X")
ylabel("Y")
legends(["Sinus 1","Sinus 2","Sinus 3"],[5,-2,3],opt=1)
xgrid(2)

plot2d(x',[f1' f2' f3'],style=[5,-2,3],frameflag=4,axesflag=5)

*/
/*
//Exercice 2

t=linspace(-5*%pi,5*%pi)
X=cos(t)
Y=sin(t)
Z=t

xlabel("X")
ylabel("Y")
zlabel("Z")
courbe = param3d(X,Y,Z,15,60,"X@Y@Z")
*/

//Exercie 3
/*
x=linspace(-1,1,20)
y=linspace(-1,1,20)

function z=f(x,y)
    z=x*x-y*y
endfunction

z=feval(x,y,f)
plot3d(x,y,z,"X@Y@Z")

p = gca().children;

p.color_mode=3;
p.hiddencolor=5;

*/
/*
x=linspace(-2*%pi,2*%pi,20)
y=x
function z1=f(x,y)
    z1=sin(x)+sin(y)
endfunction
function z2=g(x,y)
    z2=sin(x).*cos(y)
endfunction
function z3=h(x,y)
    z3=sin(x).^2-cos(y).^2
endfunction

z1=feval(x,y,f)
z2=feval(x,y,g)
z3=feval(x,y,h)

xsetech([0,0,0.5,0.5]);
plot3d(x,y,z1)
xsetech([0.5,0,0.5,0.5]);
plot3d(x,y,z2)
xsetech([0.5,0.5,0.5,0.5]);
plot3d(x,y,z3)
*/
/*

plot2d(0,0,-1,"010"," ",[-5,-5,10,10])
xset("color",3)
xfrect(0,4,2,4)
xset("color",5)
xrect(2,4,2,2)
xset("color",2)
xset("thickness",5)
xset("line style",4)
xrect(4,6,2,2)
xset("color",7)
xfrect(6,8,4,2)

*/
/*

plot2d(0,0,-1,"011"," ",[-10,-10,10,10],frameflag=4)
xfarc(0,4,2,4,0,360*64)
xarc(2,4,2,2,0,360*64)
gce().foreground=5
xarc(4,6,2,2,0,360*64)
gce().foreground=2
gce().line_style=5
gce().thickness=3
xfarc(6,8,4,2,0,360*64)
gce().background=7
*/












