#!/usr/bin/python3
from peip3 import *

def replace(f,entree,sortie):
	for line in f:
		if regsearch(entree,line):
			print(regsub(entree,sortie,line),end='')
			
			 

def url_builder(string):
	out = regsearch(" ","%20",string)
	

def main():
	
main()
