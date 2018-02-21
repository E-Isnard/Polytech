#!/usr/bin/python3
from peip3 import *


def read_lines(file):
	"Fonction qui crée une liste à partir des lignes d'un fichier"
	f = open(file,"r") 
	liste = []
	for line in f:
		liste.append(line)
	return liste

def main():
	lines = read_lines(argv[1])
	lines = sort(lines)
	for line in lines:
		print(line,end="")
	
main()
	
	  

