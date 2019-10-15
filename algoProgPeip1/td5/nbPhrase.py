def isDot(char):
    if char in "?.!":
        return True
    else:
        return False

def nbDotWhile(string):
    i=0
    n=0
    while i < len(string):
            
        if isDot(string[i]):
            n += 1
            if string[i] + string[i-1] + string[i-2] == "...":
                n -= 2
        i += 1
    return n        

def nbDotFor(string):
    n=0
    for char in string:
        if isDot(char):
            n += 1
            
    return n
print(nbDotWhile("Coucou! Adieu... ça va ?"))
