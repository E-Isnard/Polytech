#!/usr/bin/python3
from peip3 import *

def main():
	if len(argv) == 1:
		arg = "*"
	else:
		arg = "/".join(argv[1:])
		arg = arg + "/*" 	
	for file in direntries(arg):
		print(basename(file))
	
main()
	
	  

