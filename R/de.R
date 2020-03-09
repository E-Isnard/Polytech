#Somme des 4 lancés suit une loi binomiale 
#Succès= obtenir 6, proba=1/6
#donc X~Binom(4,1/6)
#P(X>0)=1-P(x=0) = 1-(5/6)^4

pTh = 1-(5/6)^4
print(pTh)


exp = sample(1:6,4*100000,replace=TRUE)
m = matrix(exp==6,4,100000)
m2 = colSums(m)
print(m2)
m3 = (m2>0)
print(m3)
pExp = mean(m3)

print(pExp)
print(pTh)


