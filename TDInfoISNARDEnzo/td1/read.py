#!/usr/bin/python3
from peip3 import *


def main():
	f = open("hello.py","r")
	for line in f:
		print(line,end='')
	close(f)


main()
