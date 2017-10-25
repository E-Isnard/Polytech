def facto(n):
	s = 1
	for i in range(n):
		s *= i+1
	return(s)

print(facto(5))

