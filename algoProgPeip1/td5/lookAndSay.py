def lookAndSay(term):
    term = str(term)
    last = term[0]
    n = 1
    result = ''
    for i in range(1,len(term)):
        if last == term[i]:
            n += 1
        else:
            result += str(n)+ last
            n = 1
        last = term[i]
    result += str(n)+last
    return result
def audioActive():
    term = 1
    nbTerme = input('Combien de termes ? (>=2)? ')
    for i in range(int(nbTerme)):
        if i == 0:
            print('Terme 1: 1')
        #elif i == 1:
            #print('Terme 2: 11')
        else:
            term = lookAndSay(term)
            print('Terme {}: {}'.format(i,term))
audioActive()
#print(lookAndSay(11))
