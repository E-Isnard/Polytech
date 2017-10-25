from lireListe import *

def insertNb(liste,nb):
    i = 0
    if len(liste) == 0:
        liste.append(nb)
        return liste
    else:
        while (i < len(liste)) and (nb > liste[i]):
            i += 1
        liste.insert(i,nb)
        return liste 


def insertList(liste1,liste2):
    liste1.sort()
    liste2.sort()
    j = 0
    while j < len(liste2):
        liste1 = insertNb(liste1,liste2[j])
        j += 1
    return liste1


liste1 = lireListeEntier("Une liste: ")
liste2 = lireListeEntier("Une liste: ")
#nb = int(input("un entier: "))
print(insertList(liste1,liste2))




