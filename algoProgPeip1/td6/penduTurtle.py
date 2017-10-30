from turtle import *
import random
import time
import os

fichier = open("dicoFrancais.txt")
dico = []
ligne = "aha"
while ligne != "":
    ligne = fichier.readline()
    dico.append(ligne)
fichier.close()

motMystere = random.choice(dico)

while 




"""

#dessine la base
forward(100)
#dessine le poteau
forward(-50)
left(90)
forward(200)
#dessine la fin du poteau
right(90)
forward(100)
right(90)
forward(50)

#dessine la tete
up()
forward(30)
left(90)
down()
circle(15)
#dessine le corps
right(90)
forward(50)
#dessine les bras
forward(-30)
right(45)
forward(20)
forward(-20)
left(90)
forward(20)
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


time.sleep(3)
"""
