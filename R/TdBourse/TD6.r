# Charge les données et montre quelques analyses

# Charger les données
source('TD6_chargeDonnees.r')
# Cela permet de définir
# 1. TD6dataframe -- la dataframe pour ce TD6
# 2. cinqMinutesTempsEnHeure -- une liste des temps de départ des intervalles de 5 minutes
# 3. getTempsAttenteEnSecondes() -- une fonction qui retourne une liste des temps d'attente
# en secondes pour un intervalle de 5 minutes pour un jour.
# Voir TD6_chargeDonnees.r pour plus de détails
  
testTD6 = function()
{
  print(head(cinqMinutesTempsEnHeure))
  print(head(TD6dataframe))
  # On récupère les temps d'attente pour le 6ème intevalle de 5 minutes du jour 1
  x = getTempsAttenteEnSecondes(20140303, cinqMinutesTempsEnHeure[6])
  # round parfois nécessaire selon l'export .csv
  x=round(x)
  print(x)
  
  # 2 plots dans l'affichage
  opar = par('mfrow'=c(2,1))
  plot(x, main='Temps entre les opérations',ylab="Temps en secondes")
  hist(x,breaks=0:12, main="Histogramme de temps entre les opérations", xlab="Temps en secondes",ylab="Fréquence")
  # On réinitialise sur un seul plot à l'affichage
  par(opar)
}
testTD6()

# On explore les hypothèses disant que le temps d'attente dans les intervalles de 5 minutes
# sont exponentiels, par des affichages d'histogramme
print(length(cinqMinutesTempsEnHeure))

dt = 20140303

t = cinqMinutesTempsEnHeure[1]
x = getTempsAttenteEnSecondes(dt, t)
x=round(x)
opar = par('mfrow'=c(2,1))
plottitle = paste('Temps entre les opérations :',dt, ", t =", t, sep=' ')
plot(x, main=plottitle, ylab="Temps en secondes")
breaks = 0:(floor(max(x)+1))
histtitle = paste("Histogramme pour",dt, ': t =', t, sep=' ')
hist(x,breaks=breaks, main=plottitle, xlab="Temps en secondes",ylab="Fréquence")
par(opar)

t = cinqMinutesTempsEnHeure[2]
x = getTempsAttenteEnSecondes(dt, t)
x=round(x)

opar = par('mfrow'=c(2,1))
plottitle = paste('Temps entre les opérations :',dt, ", t =", t, sep=' ')
plot(x, main=plottitle, ylab="Temps en secondes")
breaks = 0:(floor(max(x)+1))
histtitle = paste("Histogramme pour",dt, ': t =', t, sep=' ')
hist(x,breaks=breaks, main=plottitle, xlab="Temps en secondes",ylab="Fréquence")
par(opar)

# Plusieurs en même temps avec une pause de 0.5s
for (n in c(10,25,35,50,60,70,77,78))
{
  t = cinqMinutesTempsEnHeure[n]
  x = getTempsAttenteEnSecondes(dt, t)
  opar = par('mfrow'=c(2,1))
  plottitle = paste('Temps entre les opérations :',dt, ", t =", t, sep=' ')
  plot(x, main=plottitle, xlab="Temps en secondes")
  breaks = 0:(floor(max(x)+1))
  histtitle = paste("Histogramme pour",dt, ': t =', t, sep=' ')
  hist(x,breaks=breaks, main=plottitle, xlab="Temps en secondes")
  par(opar)
  # pause de 0.5s
  Sys.sleep(0.5)
}
dtheta = 0.02
intervalleTheta = seq(0,8,dtheta)
vraisemblance = exp(-x/intervalleTheta)/intervalleTheta

anterieur = posterieur # la probabilité postérieure d’avant devient la
# nouvelle probabilité antérieure
numerateur = anterieur*vraisemblance
posterieur = numerateur/(dtheta*sum(numerateur))

print(posterieur)

