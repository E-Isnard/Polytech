# Charge la dataframe et définit une fonction et une liste pratiques

TD6dataframe = read.csv('TD6dataframe.csv')

# Vecteur contenant la valeur en heures des heures de 9:30 à 16:00 par intervalle de 5 minutes
cinqMinutesTempsEnHeure = round(seq(570,955,5)/60, digits=3)

getTempsAttenteEnSecondes = function(dt, cinqMinutesInt)
{
  # Retourne tous les temps d'attente en secondes pour un intervalle de 5 minutes sur un jour
  
  # dt is the date : ie 20140303, 20140304, 20140305 ou 20140306
  # cinqMinutesInt est le départ de l'intervalle de 5 minutes, en heures à partir de
  # minuit avec exactement 3 décimales - pris à partir de cinqMinutesTempsEnHeure
  
  # Logique booléenne : multiplication de 0s et de 1s donne 1 que si les deux facteurs sont 1 
  b = (TD6dataframe[,'Date'] == dt)*(TD6dataframe[,'cinqMinutesInt']==cinqMinutesInt)
  # On récupère les temps en secondes
  y = TD6dataframe[b==1,'heureNombre']*24*60*60
  # On calcule la différence des temps (i+1)-i, ce qui donne le temps d'attente entre chaque opération
  return(diff(y))
}
