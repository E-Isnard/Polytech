def base(n,b):
    res = ""
    q = 1
    while q != 0:
        q = n//b
        r = n % b
        res = str(r) + res
        n = q
    
    return res


n = int(input("nombre ? "))
b = int(input("base ? "))
print(base(n,b))

