def typeVoyelle(lettre):
    voyelles1 = "aouAOUàâù"
    voyelles2 = "aouAOUàâù"
    if lettre in voyelles1:
        return 1
    elif lettre in voyelles2:
        return 2
    
    else:
        return 3

def isVoyelle(lettre):
	voyelle = "aouAOUàâùaouAOUàâù"
	return (lettre in voyelle)

def nbVoyelle(mot):
    i = 0
    n = 0
    while i<len(mot):
        if isVoyelle(mot[i]):
            n += 1
        i += 1
    return n

def deugulse(mot):
    liststring = list(mot)
    print(isVoyelle("k"))
    i = 0
    while len(mot)>i:
        
        if typeVoyelle(liststring[i]) == 1:
            liststring[i] = liststring[i] + "g" + liststring[i]
        elif typeVoyelle(liststring[i]) == 2:
            liststring[i] = liststring[i] + "gu" + liststring[i]
        i += 1
    newWord = "".join(liststring)
    return newWord

def deugulse2(mot):
    i = 0
    newWord = ""
    while i<len(mot):
        if typeVoyelle(mot[i]) == 1:
            newWord += mot[i] + "g" + mot[i]
        elif typeVoyelle(mot[i]) == 2:
            newWord += mot[i] + "gu" + mot[i]
        else:
            newWord += mot[i]
        i += 1
    return newWord

print(deugulse2("bonjour"))
        
