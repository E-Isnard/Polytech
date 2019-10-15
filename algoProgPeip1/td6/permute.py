from random import randint

def permute(string):
    out = []
    l = len(string)
    x = randint(0,l)
    for i in range(l):
        out.append(string[(i+x)%l])
    newWord = "".join(out)
    return newWord
print(permute("bonjour"))
