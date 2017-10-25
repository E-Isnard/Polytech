#!/usr/bin/python3

from lireListe import *

def moyenne(notes):
    s = 0
    for note in notes:
        s += note
    s /= len(notes)
    return s

notes = [20,10,0,15,16,17]

def moyennePonderee(notes,coeff):
    moyenne = 0
    sCoeff = 0
    i = 0
    while i < len(notes):
        moyenne += notes[i] * coeff[i]
        sCoeff += coeff[i] 
        i += 1
    moyenne /= sCoeff
    return moyenne

    

notes = lireListeEntier("Entrer la liste des notes: ")
coeff = lireListeEntier("Entrer la liste des coefficients correspondants: ")

print(moyennePonderee(notes,coeff))
#print(moyenne(notes))
