#!/usr/bin/python3

def logFrac(n):
	"Détermine le log binaire du dénominateur de n"
	i = 0
	while n < 1:
		n *= 2
		i += 1
	if n == 1:
		return "1/2^{}".format(i)
	else:
		return None

def afficherTour(l):
	"Affiche R si la liste l contient un 1 et B si elle contient un -1"
	out = ""
	for i in l:
		if i == 1:
			out += "R"
		else:
			out += "B" 
	
	return out

def valTour(l):
	i = 0
	if l[0] == "R":
		c = 1
		val = 1
	else:
		c = -1
		val = -1
	while i < len(l)-1:
		if l[i] == l[i+1]:
			c *= 1/2
			val += c
			
		else:
			c *= -1/2
			val += c
		i += 1
	return val
	
			
		
		

print(valTour("BRR"))
		
