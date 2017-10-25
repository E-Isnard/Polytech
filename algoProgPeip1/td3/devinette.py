import random

nb_tentative = 0
x = random.randint(1,100)
print(x)
reponse = ""
print("Vous devez trouver un nombre compris entre 1 et 100")
print("Vous avez au pus 7 tentatives")

while nb_tentative != 7 and reponse != x:
    reponse = int(input("Le nombre ? : "))
    if reponse > x:
        print("Trop grand")
    elif reponse < x:
        print("Trop petit")
    else:
        print("GG !")
    nb_tentative += 1

if nb_tentative == 7:
    print("Vous avez perdu =(")
else:
    print("Vous avez gagné en {} tentative(s)".format(nb_tentative))
    
