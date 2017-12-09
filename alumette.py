from random import *

def afficher(nbAlumettes):
    print("| "*nbAlumettes)

def checkWin(nbAlumettes):
    if nbAlumettes <=0:
        return True
    else:
        return False

def ia(nbAlumettes):
    if nbAlumettes > 3:
        if (nbAlumettes-1)%4 == 0:
            return 1
        elif (nbAlumettes-2)%4 == 0:
            return 2
        elif (nbAlumettes-3)%4 == 0:
            return 3
        else:
            return randint(1,3)
    else:
        return nbAlumettes





nbAlumettes = randint(5,10)
afficher(nbAlumettes)
while checkWin(nbAlumettes) == False:
    tirage = 0
    while 1>tirage or 3<tirage:
        tirage = int(input("Joueur 1: "))
    nbAlumettes -= tirage
    afficher(nbAlumettes)
    if checkWin(nbAlumettes):
        print("Le joueur 1 a gagné")
    else:
        tirage = ia(nbAlumettes)
        #while 1>tirage or 3<tirage:
            #tirage = int(input("Joueur 2: "))
        nbAlumettes -= tirage
        afficher(nbAlumettes)
        print("L'IA a pris {} alumette(s)".format(tirage))
        if checkWin(nbAlumettes):
            print("L'IA a gagné")
    