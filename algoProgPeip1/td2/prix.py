prix = int(input("prix ? "))

if prix > 1000:
    print("trop cher")
elif 100<prix<1000:
    print("j'achète meme si c'est pas donné")
else:
    print("c'est donné ! j'achète")

print("fin des achats")
