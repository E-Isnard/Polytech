import lireListe

def mex(nbList):
    nbList.sort()
    i = 0
    while i in nbList:
        i += 1
    return i
        

nbList = [1,6,1,8,9,1,6,0]

print(mex(nbList))
