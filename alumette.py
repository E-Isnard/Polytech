#!/usr/bin/python3
# -*- coding: utf-8 -*-
from random import *

def afficher(nbAlumettes):
    print("| "*nbAlumettes)

def checkWin(nbAlumettes):
    if nbAlumettes <=0:
        return True
    else:
        return False

def ia(nbAlumettes):
    """Ne marche seulement avec la règle [1,2,3]"""
    if nbAlumettes > 3:
        if nbAlumettes%4 == 0:
            return 1
        else:
            return nbAlumettes%4
        
    else:
        return nbAlumettes

def make_rule():
    length = randint(3,4)
    rule = sample(range(1,9),length)
    rule.sort()
    return rule

rule = make_rule()
nbAlumettes = randint(10,20)
print("Le jeu commence avec {} alumettes".format(nbAlumettes))
print("Les règles sont {}".format(rule))
afficher(nbAlumettes)

while checkWin(nbAlumettes) == False:

    tirage = 0
    while tirage not in rule:
        tirage = int(input("Joueur 1: "))
    nbAlumettes -= tirage
    afficher(nbAlumettes)
    if checkWin(nbAlumettes):
        print("Le joueur 1 a gagné")
    else:
        tirage = ia(nbAlumettes)
        nbAlumettes -= tirage
        afficher(nbAlumettes)
        print("L'IA a pris {} alumette(s)".format(tirage))
        print("Il reste {} alumette(s)".format(nbAlumettes))
        if checkWin(nbAlumettes):
            print("L'IA a gagné")