#!/usr/bin/python3
from peip3 import *

def main():
	if len(argv) == 1:
		for file in direntries("*"):
			print(file)
	if len(argv) == 2:
		if not(exists(argv[1])) and not(isdir(argv[1])):
			print(argv[1] +":Votre argument n'est pas valide >:c\n")
		else:
			for file in direntries(argv[1] + "/*"):
				print(basename(file))
	else:
		for arg in argv[1:]:
			if not(exists(arg)) and not(isdir(arg)):
				print(arg + ": Cet argument n'est pas valide >:c\n")
			else:
				print(arg+":")
				for file in direntries(arg + "/*"):
					print(basename(file))
		
	
main()	
	  

