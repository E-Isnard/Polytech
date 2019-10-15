#!/usr/bin/python3
from peip3 import *

def main():
	if len(argv) == 1:
		#On vérifie si l'utilisateur a entré un paramètre
		print("Aucun paramètre en entrée")
	
	elif exists(argv[1]) and isfile(argv[1]):
		#On vérifie si l'argument est un fichier
		#Si oui on affiche le contenu du fichier
		f = open(argv[1],"r")
		for line in f:
			print(line,end="")
	else:	
		#Sinon on affiche les arguments
		for arg in argv[1:]:
			print(arg) 

main()
	
	  

