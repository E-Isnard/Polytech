#36 couples possibles avec une proba égale
#Les couples dont la somme fait 7 sont (1,6),(6,1),(5,2),(2,5),(3,4),(4,3)
#donc on a 6/36=1/6 chance d'avoir une somme qui fait 7

de1 = sample(1:6,10000,replace=TRUE)
de2 = sample(1:6,10000,replace=TRUE)

sde = de1+de2
print(sde)

pExp = mean(sde==7)
pTh = 1/6

print(pExp)
print(pTh)