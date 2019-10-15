#!/usr/bin/python3
from peip3 import *


def main():
	fp = open(argv[1],"w")
	fc= open([argv[2],"r")
	for line in fc:
		print(line,file=fp,end="")
	close(fp)
	close(fc)
main()

