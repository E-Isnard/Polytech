#!/usr/bin/python3
from peip3 import *

def search(f):
	for line in f:
		if regsearch(argv[1],line):
			print(line,end='')


def main():
	if len(argv) == 2:
		search(stdin)
	elif len(argv) == 3:
		f = open(argv[2],'r')
		search(f)
main()
