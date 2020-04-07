source('TD6_chargeDonnees.r')

dt = 20140304 # date = 4 Mars

maxV = c()

for (t in cinqMinutesTempsEnHeure) {

  donneeActuelle = getTempsAttenteEnSecondes(dt, t) # obtenir les temps d'attente pour ce jour sur cet intervalle
  ndonnee = length(donneeActuelle)

dtheta = 0.01
thetaInt = seq(dtheta, 8, dtheta)


# Probabilité antérieure uniforme
numerateurAntUnif = rep(1, length(thetaInt))
probAntUnif = numerateurAntUnif / (dtheta * sum(numerateurAntUnif))

posterieurMat = matrix(NA, nrow=length(thetaInt), ncol=ndonnee)
initialProbAnt = probAntUnif

probAnt = initialProbAnt
for (j in 1:ndonnee)
{ 
  
  tempsAttente = donneeActuelle[j]
  vraisamblance = exp(-tempsAttente/thetaInt)/thetaInt
  numerateur = probAnt*vraisamblance
  Tt = dtheta*sum(numerateur)
  probPost = numerateur/Tt
  
  
  
  posterieurMat[,j] = probPost
  probAnt = probPost
}


maxV = append(maxV,thetaInt[which.max(posterieurMat[,ndonnee])])

thetamin = 0
thetamax = max(thetaInt)
ymin = 0
ymax = max(posterieurMat,initialProbAnt)
# Crée un plot vide avec les bons intervalles
# s = paste('4 Mars 2014 à ', t, " heures" )
# plottitle = paste(c("Plot de tous les postérieures (et probAnt)", s))
# plot(c(thetamin,thetamax),c(ymin,ymax), type='n', xlab="theta", ylab="densité",
#      main = plottitle)
# lines(thetaInt, initialProbAnt, col='red')
# for (j in 1:ndonnee)
# {
#   # R nous laisse boucler sur sa liste de couleurs en utilisant col=j
#   lines(thetaInt, posterieurMat[,j], col=j,type="l")
# }

plottitle="Prb postérieure"


# plot(c(thetamin,thetamax),c(ymin,ymax),type='n',xlab="theta",ylab="densité",main=plottitle)
# lines(thetaInt,posterieurMat[,ndonnee],col="red")
   
}
print(maxV)
plot(cinqMinutesTempsEnHeure, maxV,type="l",col="blue")