mois = int(input("Quel mois ? (entre 1 et 12): "))

if mois == 2:
    print("Il y a 29 jours les années bissextiles et 29 sinon dans le mois {}".format(mois))

elif mois%2 == 0:
    if mois < 8:
        print("Il ya 30 jours dans le mois {}".format(mois))
    else:
        print("Il y a 31 jorus dans le mois {}".format(mois))
else:
    if mois < 8:
         print("Il ya 31 jours dans le mois {}".format(mois))
    else:

         print("Il ya 30 jours dans le mois {}".format(mois))

