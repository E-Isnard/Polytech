def cryptoKey(key):
    alpha = "abcdefghijklmnopqrstuvwxyz"
    listAlpha = list(alpha)
    listWordsKey = key.split(" ")
    listKey = list(key)
    for char in key:
        if char in listAlpha:
            listAlpha.remove(char)
        else:
            continue
    new_alpha = "".join(listAlpha)
    new_key = listWordsKey[0] + new_alpha
    return new_key

def CryptoString(string,key):
    listString = list(string)
    listNewKey = list(cryptoKey(key))
    for i in range(len(listNewKey)):
        pass
    newString = "".join(listString)
    return newString

print(CryptoString("coucou","j'aime les cerises"))
    

