#!/usr/bin/python3
import sys
import os
def generationSuivante(liste):
	out = []
	out.append(liste[0]-1)
	out.append(liste[0])
	for i in range(len(liste)-1):
		out.append((liste[i]+liste[i+1])/2)
		out.append(liste[i+1])
	out.append(liste[len(liste)-1]+1)
	return out

	
def arbre(n):
	out = [-1,0,1]
	for i in range(n):
		out = generationSuivante(out)
	return out	

print("Quelle génération de l'arbre voulez-vous ?\n")
n = ""
for i in sys.stdin:
    n += i
n = int(n)
print(arbre(n))	
