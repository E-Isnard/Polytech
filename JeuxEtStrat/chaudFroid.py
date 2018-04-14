#!/usr/bin/python3
from sys import *
from os import *
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
	out = [0]
	for i in range(n):
		out = generationSuivante(out)
	return out	
		
def arbreDiad(n):
	out = []
	for el in arbre(n):
		out.append(diad(el))
	return out
def diad(n):
	i = 0
	while n != int(n):
		n *= 2
		i += 1
	if i == 0:
		return(str(n))
	else:
		return (str(int(n))+"/"+str(2**i))		


n = int(argv[1])
print(arbreDiad(n))
#arbre = arbre(int(n))
#print(arbre)
#print(len(arbre))
