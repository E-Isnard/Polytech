table = int(input("Quelle table voulez-vous affichez ?: "))
nb_de_fois = int(input("Jusqu'à quelle valeur?: "))

i = 1
print("Table de {} jusqu'à {}".format(table,nb_de_fois))
while i < nb_de_fois + 1:
    print(i," x {}:".format(table),i * table)
    i += 1

print("Fin de la table")
