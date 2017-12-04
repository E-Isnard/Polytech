from random import *

def afficher(nbAlumettes):
    print("| "*nbAlumettes)

def checkWin(nbAlumettes):
    if nbAlumettes <=0:
        return True
    else:
        return False


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
        tirage = 0
        while 1>tirage or 3<tirage:
            tirage = int(input("Joueur 2: "))
        nbAlumettes -= tirage
        afficher(nbAlumettes)
        if checkWin(nbAlumettes):
            print("Le joueur 2 a gagné")
    