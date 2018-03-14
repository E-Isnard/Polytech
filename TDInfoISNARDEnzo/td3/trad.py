#!/usr/bin/python3
from peip3 import *

def replace(f,entree,sortie):
    for line in f:
        if regsearch(entree,line):
            print(regsub(entree,sortie,line),end='')
            
             

def url_builder(string):
    out = regsub(" ","%20",string)
    out = "https://translate.google.fr/#en/fr/it%20rains"+out
    return out

def translate(url):
    page = urlopen(url)

    for line in page:
        if regsearch("TRANSLATED_TEXT",line):
            print(line)
    

def main():
    translate(url_builder("oui oui oui"))
    
main()
