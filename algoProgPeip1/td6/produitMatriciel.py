from lireListe import lireListeEntier

def lireM(ligne,colonne):
    matrice = []
    for i in range(ligne):
        liste = lireListeEntier("ligne de taille {}: ".format(colonne))
        matrice += liste
    return matrice

def produitM(matrice,vecteur):
    produit = []
    s = 0
    i = 0
    for i in range(ligne):
        for j in range(colonne):
            s += matrice[j] * vecteur[j]
        produit.append(s)
        s = 0

    return produit

ligne = int(input("nombre de ligne: "))
colonne = int(input("nombre de colonne: "))
print("lecture d'une matrice {} {}".format(ligne,colonne))
matrice = lireM(ligne,colonne)
print("lecture d'un vecteur de taille {}".format(colonne))
vecteur = lireM(1,colonne)

print(produitM(matrice,vecteur))
