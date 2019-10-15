def capsWord(string):
    new_list = []
    listWord = string.split(" ")
    for word in listWord:
        char = list(word)
        char[0] = char[0].upper()
        new_word = "".join(char)
        new_list.append(new_word)
    string = " ".join(new_list)
    return string

def capsWord2(string):
    i = 0
    newString = ""
    while i < len(string):
        if i == 0:
            newString += string[i].upper()
        else:
            if string[i-1] is " ":
                newString += string[i].upper()
            else:
                newString += string[i]
        i += 1
    return newString
            
print(capsWord2("coucou je suis enzo "))
