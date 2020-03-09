#Vecteur contenant les différents succès ou echec du yam
yamV = list()

#On essaie 10000 fois
for(j in 1:10000){
  #On commence avec 5 dés
  n=5
  yam = FALSE
  #On a le droit à 3 essais
  for (i in 1:3) {
    lance = sample(1:6,n,replace=TRUE)
    nb6 = sum(lance==6)
    #Si le dé est égal à 6 on ne le relance pas
    n=n-nb6
    #Quand on tous les dés sont égaux à 6 on a réussi à faire un Yam de 6
    if(n==0){
      yam=TRUE
      break
    }
  }
  #On rajoute l'essaie au vecteur
  yamV = append(yamV,yam)
}

#On calcule la probabilité de faire un yam
print(mean(yamV==TRUE))
