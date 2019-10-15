from turtle import *
import time

l =int(input("Quelle doit etre la taille des petales ?"))
n = int(input("Combien de petales ?"))

def polygone(n,l):
	for i in range(n):
		forward(l)
		left(360/n)
	
for i in range(n):
	polygone(4,l)
	left(360/n)


time.sleep(3)
