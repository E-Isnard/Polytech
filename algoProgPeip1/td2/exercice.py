nom = input("Quel est votre nom?: ")
prenom = input("Quelle est votre prenom?: ")
sport = input("Quel sport pratiquez vous ?: ")
FC = int(input("Quel est votre FC?"))
print("Entrainement de {0} {1}:".format(prenom,nom))

if sport ==  "aucun":
    if FC>70:
        print("Va falloir vous y mettre")
    else:
        print("bon coeur,entrainement inutile")
else:
    if sport == "collectif":
        print("Ne depassez pas {} FC".format(str(1.5*FC)))
    elif sport == "combat":
        print("Ne dépassez pas {} FC".format(str(2*FC)))
    elif sport == "endurance":
              print("Ne dépassez pas {} FC".format(str(1.2*FC)))

              


