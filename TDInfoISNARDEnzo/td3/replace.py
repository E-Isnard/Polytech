#!/usr/bin/python3
from peip3 import *

def replace(f,entree,sortie):
	for line in f:
		if regsearch(entree,line):
			print(regsub(entree,sortie,line),end='')
			
			 


def main():
	if len(argv) == 3:
		replace(stdin,argv[1],argv[2])
	elif len(argv) == 4:
		f = open(argv[4],'r')
		replace(f,argv[1],argv[2])
main()
