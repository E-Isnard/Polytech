#!/usr/bin/python3
from peip3 import *

def url_builder(string):
    out = regsub(" ","%20",string)
    out = "http://translate.google.com/translate_t?text="+out+"&langpair=en|fr"
    return out

def translate(url):
    page = urlopen(url)
    pattern = "^.*;TRANSLATED_TEXT='([^']*)';.*$"
    for line in page:
        if regsearch("TRANSLATED_TEXT",line):
            return regsub(pattern,"\\1",line)

def main():
    print(translate(url_builder(input("Quelle phrase voulez-vous traduire?:\n"))))

main()
