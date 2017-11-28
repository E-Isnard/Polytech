# -*- coding: utf-8 -*-

def cesar_crypt(string,key):
    string = string.upper()
    string_crypted = []
    for letter in string:
        nb_letter = ord(letter)-65
        nb_letter_cryped = (nb_letter+key)%26
        letter_crypted = chr(nb_letter_cryped + 65)
        string_crypted += letter_crypted
    string_crypted = "".join(string_crypted)
    return string_crypted

def cesar_crack(string):
    for i in range(26):
        print(cesar_crypt(string,-i))

#Le message doit être écrit en majuscules et il est préférable qu'il ne contienne pas de caractères
#spéciaux
#print(cesar_crypt('baguette',17))

cesar_crack('CVTFUVVJKSRXLVKKV')

