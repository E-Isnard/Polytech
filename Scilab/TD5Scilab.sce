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



//Exo4
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
//Exo5
scf();
clear();
plot2d(0,0,[-10,-10,10,10]);
xgrid(2);
xfrect(0,4,2,4);
gce().background = color("white");
xfrect(2,4,2,2);
gce().background = color("white");

xrect(2,4,2,2);
gce().foreground = color("red");

xfrect(6,8,4,2);
gce().background = color("yellow");

xfarc(0,4,2,4,0,64*180);
gce().background = color("black");


xfarc(2,4,2,2,45*64,64*270);
gce().background = color("yellow");

xfarc(6,8,4,2,0,360*64);
gce().background = color("white")
*/


/*
//Exo6
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

//Exercice 7
/*
clc();
clear();
scf();
// (3,0,0), (0,2,0), (0,0,5) et (6,4,12)

x = [3 3 3 0;
     0 0 0 0;
     0 6 6 6];

y = [0 0 0 2;
     2 2 0 0;
     0 4 4 4];

z = [0 0 0 0;
     0 0 5 5;
     5 12 12 12];

tcolor = [2 3 2 2]';

subplot(2,2,1);
plot3d(x,y,list(z,tcolor));
xtitle('Un beau tétraèdre');
*/

//Exercice 8
/*
clc();
clear();
scf();
// (1,0,0), (1,0,1), (2,1,0) (2,1,1) (1 2 0) (1, 2, 1) (0 1 0) (0 1 1)

x = [1 1 1 1 1 1;
     1 1 2 1 1 2;
     2 0 1 2 0 1;
     2 0 0 2 0 0];

y = [0 0 0 2 2 2;
     0 0 1 2 2 1;
     1 1 2 1 1 0;
     1 1 1 1 1 1];

z = [0 0 0 1 1 1;
     1 1 0 0 0 1;
     1 1 0 0 0 1;
     0 0 0 1 1 1];

tcolor = [2 3 4 5 6 7]';

subplot(2,2,1);
plot3d(x,y,list(z,tcolor));
xtitle('Un beau cube');
*/










