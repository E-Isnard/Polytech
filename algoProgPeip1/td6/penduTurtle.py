from turtle import *
import random
import os

#Listes des fonctions

def choisirMot():
     """fonction qui choisit un mot parmis une liste de mots
    dans un fichier texte"""
    fichier = open("dicoFrancais.txt", encoding="ISO-8859-1")
    dico = []
    ligne = " "
    while ligne != "":
        ligne = fichier.readline()
        ligne = ligne[:-1]
        dico.append(ligne)
    fichier.close()
    return random.choice(dico)

def motMasqueFunc(lettresTrouvees,motComplet):
    """fonction qui affiche un mot incomplet 
    dépendant des lettres qu'on a trouvé"""
    motMasque = ""
    for lettre in motComplet:
        if lettre in lettreTrouvees:
            motMasque += lettre
        else:
            motMasque += "*"
    return motMasque

def drawPendu(nbDevie):
    """fonction qui dessine le pendu"""
    if nbDeVie == 6:
        #dessine la base
        forward(100)

    elif nbDeVie == 5:
        #dessine le poteau
        forward(-50)
        left(90)
        forward(200)

    elif nbDeVie == 4:
        #dessine la fin du poteau
        right(90)
        forward(100)
        right(90)
        forward(50)

    elif nbDeVie == 3:
        #dessine la tete
        up()
        forward(30)
        left(90)
        down()
        circle(15)

    elif nbDeVie == 2:
        #dessine le corps
        right(90)
        forward(50)

    elif nbDeVie == 1:
        #dessine les bras
        forward(-30)
        right(45)
        forward(20)
        forward(-20)
        left(90)
        forward(20)

    elif nbDeVie == 0:
        #dessine les jambes
        forward(-20)
        right(45)
        forward(30)
        right(45)
        forward(20)
        forward(-20)
        left(90)
        forward(20)
        up()
        goto(0,-20)
        write("PENDU HAHA !")
        if os.name == "nt":
            os.system("pause")
        elif os.name == "posix":
            os.system("echo Appuyez sur une touche pour continuer... && read variable")
            #la variable de read est inutile


#####################################
# Programme principal

motComplet = choisirMot().lower()
#print(motComplet)
lettreTrouvees = []
nbDeVie = 7
motMasque = ""

while motMasque != motComplet and nbDeVie > 0:
    motMasque = motMasqueFunc(lettreTrouvees,motComplet)
    print("mot trouvé : {} ; vous avez {} vie(s)".format(motMasque,nbDeVie))
    lettre = input("Veuillez saisir une lettre: ").lower()
    if lettre in lettreTrouvees:
        print("Vous avez déjà choisi cette lettre")
    elif lettre in motComplet:
        lettreTrouvees.append(lettre)
        print("Bien joué")
    else:
        nbDeVie -= 1
        print("La lettre n'est pas dans le mot")
        drawPendu(nbDeVie)
if nbDeVie == 0:
    print("Vous avez perdu ! Le mot complet était {}".format(motComplet))
else:
    print("Vous avez gagné !")
