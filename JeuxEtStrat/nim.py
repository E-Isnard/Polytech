#!/usr/bin/python3

def mex(l):
	i = 0
	while i in l:
		i += 1
	return i

def strNim(n):
	if n == 0:
		return "0"
	
	elif n==1:
		return "*"
	else:
		return "*"+str(n)

def decompoNim(n):
	return list(range(n))


def sommeNim(n,m):
	l = []
	l1 = decompoNim(m)
	l2 = decompoNim(n)
	if n==m:
		return 0
	
	if n==0 or m == 0:
		return max(n,m)
	
	for i in l1:
		l.append(sommeNim(n,i))
	for j in l2:
		l.append(sommeNim(m,j))
	
	return mex(l)

def valJeuUnePile(r,n):
	l = []
	if n<=0:
		return 0
	
	for i in r:
		l.append(valJeuUnePile(r,n-i))
	return mex(l)
	
def valJeu(r,t):
	s = 0
	for i in t:
		s = sommeNim(s,valJeuUnePile(r,i))
	return s 	

def strat(r,t):
	if valJeu(r,t) == 0:
		return 1
	else:
		return 2

def a(n):
	print(strNim(n))
	

a(valJeu([1,2,3],[1,3,5]))
	
	
	
	
	
