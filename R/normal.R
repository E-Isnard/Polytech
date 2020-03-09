# La fonction rnorm génére des valeurs aléatoires suivant une loi normale
# continue
# rnorm(n, mu, sigma) génére n valeurs aléatoires de la loi
# Norm(mu, sigma^2)
# Nous pouvons tester les probabilités 0.68 et 0.95 de la loi normale
# centrée réduite
x = rnorm(10000,0,1)
mean(abs(x) < 1)
mean(abs(x) < 2)

 # dnorm est la densité de probabilité
 # Loi normale centrée réduite
 z = seq(-6,6,.01) # 'sequence' de -6 à 6 par pas de 0.01
 plot(z,dnorm(z,0,1), type="l", col="blue", lwd=3)
 # Si on ajoute d'autres densités
 lines(z,dnorm(z,1,1), col="red", lwd=3)
 lines(z,dnorm(z,0,2), col="green", lwd=3)

 # pnorm est la fonction de répartition
 # Loi normale centrée réduite
 z = seq(-6,6,.01)
 plot(z,pnorm(z,0,1), type="l", col="blue", lwd=3)
 # d'autres
 lines(z,pnorm(z,1,1), col="red", lwd=3)
 lines(z,pnorm(z,0,2), col="green", lwd=3)
 
 data = rnorm(1000,0,1)
 hist(data)
 
 hist(data, breaks=2)
 