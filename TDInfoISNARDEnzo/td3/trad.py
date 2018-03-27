#!/usr/bin/python3
from peip3 import *

def get_from(options):
        i = 0
        while i < len(options):
                if options[i] == "--from":
                        return options[i+1]
                i += 1
        return "eng"

def get_to(options):
        i = 0
        while i < len(options):
                if options[i] == "--to":
                        return options[i+1]
                i += 1 
        
        return "fr"             
         

def build_sentence(liste):
        out = ""
        i = 1
        while i < len(liste):
            if liste[i] != "--from" and liste[i] != "--to": 
                out += argv[i]+" "
                i += 1
            else:
                i += 2

        return out              

def url_builder(string):
    to = get_to(argv)
    ofrom = get_from(argv)
    out = regsub(" ","%20",string)
    out = "http://translate.google.com/translate_t?text="+out+"&langpair="+ofrom + "|" + to
    return out

def translate(url):
    page = urlopen(url)
    pattern = "^.*;TRANSLATED_TEXT='([^']*)';.*$"
    for line in page:
        if regsearch("TRANSLATED_TEXT",line):
            return regsub(pattern,"\\1",line)

def main():
    out = translate(url_builder(build_sentence(argv)))
    print(out)

main()
