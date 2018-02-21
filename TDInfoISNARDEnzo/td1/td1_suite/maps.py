#!/usr/bin/python3
from peip3 import *


def build_URL():
	URL1="http://www.google.com/maps/place/"
	URL2="+".join(argv[1:])
	URL=URL1 + URL2
	return URL
 

def main():
	system("firefox "+build_URL())
	
	
main()
