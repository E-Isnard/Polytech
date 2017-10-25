def pascalFunction(l):
    out = []
    i = 1
    while i < len(l):
        out.append(l[i]+l[i-1])
        i += 1
    out.insert(0,1)
    out.insert(len(l),1)
    return out

def TriPascal(n):
    l = [1]
    for i in range(n):
        print(l)
        l = pascalFunction(l)





TriPascal(6)

#print(pascalFunction([1,1]))
