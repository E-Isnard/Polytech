# Ce code convertit les données initiales du NASDASQ (donneesBourse.csv) vers dataframeDonnesEnBourse.csv 

# donnesEnBourse possède les données pour chaque opération en bourse du NASDAQ d'une action sur 4 jours en Mars 2014
#   Colonne      | Description
#   -----------------------------------------------------------------------------
#   Date        | Date de l'opération au format YYYMMDD
#   heureNombre  | heure de l'opération comme fraction de 24 heures (0.5 = 12 midi)
#   heureHHMMSS  | heure de l'opération comme un entier au format HHMMSS
#   taille        | taille de l'opération en action
#   prix       | prix de l'opération (valeur pondérée sur toutes les opérations enregistrées à la même heureNombre)


# Analyse des données
# Charger les données
donneesBourse = read.table(file="donneesBourse.csv",sep=",", header=TRUE)
# 1er coup d'oeil
print(head(donneesBourse))
print(dim(donneesBourse))

# Plot prix en fonction de l'indice de l'opération - grossièrement le temps
plottitle =  paste(c("prix des opérations", "Dates: 3/3, 3/4, 3/5, 3/6 in 2014"))
plot(donneesBourse[,"prix"], type = 'l', col='blue',
     xlab="indice opération", ylab="prix", main=plottitle)

# Ajouter des lignes verticales au début de chaque date
liste.Dates=unique(donneesBourse[,"Date"])
for (date in liste.Dates){
  print(date)
  abline(v=sum(donneesBourse[,"Date"]< date))
}

# Regrouper les données par portion de 5 minutess
# Récupérer la colonne heureNombre de donneesBourse - temps en fraction de 24 heures, i.e 0.5=12 midi
donneesBourse.heureColonne = donneesBourse[,"heureNombre"]

# On veut arrondir inférieur (floor()) chaque temps vers le plus proche 5 minutes
# Le temps est fraction de 24 heures, donc floor(t*24*12)/12 nous donne t arrondi 
# au plus proche multiple de 5 minutes plus petit que t.
# En effet : t est fraction de 24h, donc t*24*60 est t en minutes et t*24*12 est t 
# en unité de 5 minutes. Donc floor(t*24*12) est un multiple de 5 minutes.
# Diviser par 12 convertit de nouveau vers des unités d'heures à partir de minuit
# Pour traiter le cas du début et de la fin de journée (9:30 et 16:30) on ajouter 1/2 seconde
# à chaque temps - 9:30 reste dans l'intervalle de 5 minutes commençant à 9:30 et 
# la dernière heure 15:59:59 reste dans l'intervalle de 5 minutes commençant à 15:55

demiSec= 1/(24*60*60*2)
x = floor((donneesBourse.heureColonne+demiSec)*24*12)/12
heureOperation5minute = round(x, digits = 3)

# La fonction table() avec un argument produit un tableau de comptage
# On regroupe les 4 jours pour obtenir un tableau de comptage pour chaque intervalle de 5 minutes
heureOperation5minute.tableTot = table(heureOperation5minute)
head(heureOperation5minute.tableTot)
plot(heureOperation5minute.tableTot, xlab="Temps en heures", ylab="Nombre d'opérations", main="Nombre d'opérations sur des intervalles de 5 minutes (tous les jours regroupés)", cex.main=.9)

# Pour chaque jour
# Une table : lignes = période de 5 minutes, colonnes = dates, entrée = indice des opérations
heureOperation5minute.tableJour = table(heureOperation5minute, donneesBourse[,"Date"])
print(heureOperation5minute.tableJour)

# plot
n = nrow(heureOperation5minute.tableJour)
col = c(rep('orange',n), rep('blue',n), rep('green',n), rep('red',n))
barplot(heureOperation5minute.tableJour, beside=TRUE, 
        col=col, cex.main = 1.3,  space=c(0,1), border=NA, cex.names=1,
        names.arg = c('3/3/2014', '3/4/2014', '3/5/2014', '3/6/2014'),
        main="Comptage des opérations sur des intervalles de 5 minutes pour chaque jour")

# Création du fichier csv pour la suite
dataframe = data.frame(Date = donneesBourse[,'Date'], 
                                cinqMinutesInt = heureOperation5minute,
                                heureNombre = donneesBourse.heureColonne)

write.csv(dataframe, 'TD5dataframe.csv')

