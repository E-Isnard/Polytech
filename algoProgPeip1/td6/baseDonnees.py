baseDonnees = [["Collavizza","Hélène",6],["Enzo","Isnard",14],["Reed","Lou",12]]
continuer = True
while continuer:
    print("Voulez-vous :\n\n")
    print("1.Afficher les élèves\n")
    print('2.Calculer la moyenne de la classe\n')
    print('3.Trouver le major(ie Enzo)\n')
    print('4.sortir')
    choix = int(input("Votre choix:"))
    if choix == 1:
        for i in range(len(baseDonnees)):
            print(baseDonnees[i][0],baseDonnees[i][1],end="\n")

    if choix == 2:
        somme = 0
        for i in range(len(baseDonnees)):
            somme += baseDonnees[i][2]
        print(somme/len(baseDonnees))

    if choix == 3:
        moyenneMajor = 0
        for liste in baseDonnees:
            if liste[2] > moyenneMajor:
                moyenneMajor = liste[2]
        for liste in baseDonnees:
            if liste[2] == moyenneMajor:
                major = "{} {}".format(liste[0],liste[1])
        print(major)

    if choix == 4:
        continuer = False
    else:
        print("Veuillez rentrer un nombre entre 1 et 4 (gros débile)")
