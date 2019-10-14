clf()
t = [0:0.02:2*%pi]
x = cos(t);y=sin(t)
plot2d4(x,y,style=2,rect=[-2,-2,2,2],frameflag=3,axesflag=5)
//style=2 courbe en bleu
// rect delimite la fentre par une boite
// frameflag=3 echelle isométrique
// axesflag=5 axes qui se croisent au milieu du cadre
x=[0;sqrt(2)]
plot2d(x,sqrt(2)-x,style=5,frameflag=0)
xgrid(3)
legends(['cercle' 'tangente'],[2,5],2)
