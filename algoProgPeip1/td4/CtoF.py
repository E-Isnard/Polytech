def affine(a,b,x):
    return a*x+b

def CtoF(choice,T):
    """Fonction qui prend en argument une température en C° ou F° et la 
    convertit respectivement en F° ou en C°"""
    if choice == "F":
        return affine(1/1.8,-32/1.8,T)
    elif choice == "C":
        return affine(1.8,32,T)
    else:
        return "Unité non reconnue"

choice = input("Quelle est l'unité de votre température (C ou F)?: ")
T = int(input("Température à convertir: "))

print(CtoF(choice,T))
