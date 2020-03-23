#EX 3.1
 
 #Les hypotheses sont les differents des
 #Les des prennent leurs valeurs dans 1:20
 
x=c(4,6,8,12,20)
d = sample(x,1)


pb4 = 1/5
pb6 = 1/5
pb8 = 1/5
pb12 = 1/5
pb20 = 1/5

probaM = cbind(c(1/5,1/5,1/5,1/5,1/5))

for(i in 1:20){
   lance = sample(1:d,1)
   if(lance<=4){
      p4post = 1*(pb4)/(1*pb4+4/6*pb6+4/8*pb8+4/12*pb12+4/20*pb20)
      p6post = 4/6*pb6/(1*pb4+4/6*pb6+4/8*pb8+4/12*pb12+4/20*pb20)
      p8post = 4/8*pb8/(1*pb4+4/6*pb6+4/8*pb8+4/12*pb12+4/20*pb20)
      p12post = 4/12*pb12/(1*pb4+4/6*pb6+4/8*pb8+4/12*pb12+4/20*pb20)
      p20post = 4/20*pb20/(1*pb4+4/6*pb6+4/8*pb8+4/12*pb12+4/20*pb20)
      
   }else if(lance>4 && lance <=6){
      p4post = 0*(pb4)/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p6post = 2/6*pb6/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p8post = 2/8*pb8/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p12post = 2/12*pb12/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p20post = 2/20*pb20/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
   }else if (lance>6 && lance <= 8) {
      p4post = 0*(pb4)/(0*pb4+2/6*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p6post = 0*pb6/(0*pb4+0*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p8post = 2/8*pb8/(0*pb4+0*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p12post = 2/12*pb12/(0*pb4+0*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
      p20post = 2/20*pb20/(0*pb4+0*pb6+2/8*pb8+2/12*pb12+2/20*pb20)
   }else if (lance>8 && lance<=12) {
      p4post = 0*(pb4)/(0*pb4+0*pb6+0*pb8+4/14*pb12+4/20*pb20)
      p6post = 0*pb6/(0*pb4+0*pb6+0*pb8+4/12*pb12+4/20*pb20)
      p8post = 0*pb8/(0*pb4+0*pb6+0*pb8+4/12*pb12+4/20*pb20)
      p12post = 4/12*pb12/(0*pb4+0*pb6+0*pb8+4/12*pb12+4/20*pb20)
      p20post = 4/20*pb20/(0*pb4+0*pb6+0*pb8+4/12*pb12+4/20*pb20)
   }else if (lance >12 && lance <=20) {
      p4post = 0*(pb4)/(0*pb4+0*pb6+0*pb8+0*pb12+8/20*pb20)
      p6post = 0*pb6/(0*pb4+0*pb6+0*pb8+0*pb12+8/20*pb20)
      p8post = 0*pb8/(0*pb4+0*pb6+0*pb8+0*pb12+8/20*pb20)
      p12post = 0*pb12/(0*pb4+0*pb6+0*pb8+0*pb12+8/20*pb20)
      p20post = 8/20*pb20/(0*pb4+0*pb6*pb6+0*pb8+0*pb12+8/20*pb20)
   }
   pb4=p4post
   pb6=p6post
   pb8=p8post
   pb12=p12post
   pb20=p20post
   pbPost = c(p4post,p6post,p8post,p12post,p20post)
   probaM=cbind(probaM,pbPost)
}



#print(lance)
print(d)
title = sprintf("Diagramme proba post avec le de %s",d)

barplot(probaM,col=c('red','yellow','green','blue','purple'),names=0:20,main=title)
legend(20,1,c("de4","de6","de8","de12","de20"),c('red','yellow','green','blue','purple'))
#pbPost = c(p4post,p6post,p8post,p12post,p20post);
#title = sprintf("Histogramme des probabilites posterieurs apres le lance %s",lance)
#barplot(pbPost,x,col="red",main=title,space=0.3,width=2)


