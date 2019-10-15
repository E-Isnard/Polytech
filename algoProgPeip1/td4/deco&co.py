from math import *

def surfaceAPeindre(h,L,l):
    """Fonction qui renvoie la surface totale des faces intérieures d'un cube"""
    return 2*h*l + 2*h*L + 2*L*l

def nombrePots(h,L,l,pvCouvrant):
    """fonction qui prend les caractéristiques d'une pièce et le pouvoir couvrant
    d'un pot de peinture (i.e. le nombre de m2 que le pot permet de couvrir) et qui 
    renvoie le nombre de pots nécessaires pour peindre la pièce. """

    return ceil(surfaceAPeindre(h,L,l)/pvCouvrant)


choice = ""
while choice != "o":
    h = float(input("hauteur de la pièce(en m): "))
    L = float(input("longueur de la pièce(en m): "))
    l = float(input("largeur de la pièce(en m): "))
    pvCouvrant = int(input("pouvoir couvrant du pot(en m²): "))
    nom = input("nom de la pièce: ")
    print("\nIl vous faut {} pots la/le {}\n".format(nombrePots(h,L,l,pvCouvrant),nom))

    choice = input("Quitter ?(o/n) ")

    
