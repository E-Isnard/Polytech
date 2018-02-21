#!/usr/bin/python3
from peip3 import *

def main():
	f = open(argv[1], "r")
	lines = f.readlines()
	i = 0
	while i < len(lines):
		n = 1
		while i+n < len(lines) and lines[i] == lines[i+n]:
			n += 1
		print(n,lines[i],end="")
		i += n

main()
	
	  

