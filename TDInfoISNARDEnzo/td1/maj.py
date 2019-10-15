#!/usr/bin/python3
from peip3 import *


def main():
	f = open(argv[1],"r")
	for line in f:
		new_line = line.upper()
		print(new_line,end='')
	close(f)


main()
