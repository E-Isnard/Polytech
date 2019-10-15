#!/usr/bin/python3
def puissance(n,x):
	if n == 0:
		return 1
	else:
		return x*puissance(n-1,x)


def occurence(s,c):
	if s=='':
		return 0
	else:
		if c in s:
			return 1+occurence(s[:-1],c)
		else:
			return occurence(s[:-1],c)


def maxi(l):
	if l==[]:
		return 0
	else:
		if l[0]>maxi(l[1:]):
			return l[0]
		else:
			return maxi(l[1:])


def palindrome(string):
	n = len(string)-1
	if len(string)<2:
		return True
	else:
		if string[n] != string[0]:
			return False
		else:
			return palindrome(string[1:-1])


def heron(a,n):
	if n == 0:
		return 1
	else:
		return (heron(a,n-1)+a/heron(a,n-1))/2


def fibo(n):
	if n == 0:
		return 0
	elif n == 1:
		return 1
	else:
		return fibo(n-1)+fibo(n-2)



def permu(l):
	if len(l)<2:
		return l
	else:
		out = []
		for el in l:
			remL = [x for x in l if x != el]
			for p in permu(remL):
				if type(p) != type([]):
					p = [p]
				p.insert(0,el)
				out.append(p)
		return out


l = ["a","b","c","d"]
print(permu(l))
