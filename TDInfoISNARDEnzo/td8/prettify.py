#!/usr/bin/env python3
#                                               -*- coding: utf-8 -*-
#
# prettify.py   -- Enjoliver un fichier
#
#           Author: Erick Gallesio [eg@unice.fr]
#    Creation date: 26-May-2016 08:41 (eg)
# Last file update:  1-Jun-2018 13:23 (eg)
#

from peip3 import *


# ----------------------------------------------------------------------
def print_html_begin(title):
    print('''<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="./css/wiki.css" type="text/css" rel="stylesheet" />
    <title>''' + title + '''</title></head>
  <body class="grd">''')


def print_html_end():
    print('  </body>\n</html>')

# ---------------------------------------------------------------------
def pretty(line):
    # Mettre ici le code permettant d'enjoliver une ligne
    line = regsub(">","&gt",line)
    line = regsub("<","&lt",line)
    line = regsub("%%","",line)
    line = regsub("&","&amp",line)
    line = regsub("===+=","<hr>",line)
    line = regsub("^[ \t]*LIST\{","<ul>",line)
    line = regsub("^[ \t]*LIST\[","<ol>",line)
    line = regsub("^[ \t]*\\}$","<ul/>",line)
    line = regsub("^[ \t]*\\]$","<ol/>",line)
    line = regsub("^[ \t]*#","<li>",line)
    line = regsub('^!!(.*)','<div class="sous section">\\1</div>',line)
    line = regsub('^!(.*)','<div class="section">\\1</div>',line)
    line = regsub('\\*\\*(.*)\\*\\*','<b>\\1</b>',line)
    line = regsub('~~(.*)~~','<i>\\1</i>',line)
    line = regsub('\\$\\$(.*)\\$\\$','<mark>\\1</mark>',line)
    line = regsub('__(.*)__','<u>\\1</u>',line)
    if line == "\n":
        line = "<p></p>"+line

    
    return line

# ----------------------------------------------------------------------
def get_title(fname):
    # Mettre ici le code permettant de trouver le titre 
    # du fichier fname
    res = "METTRE LE TITRE DE LA PAGE"
    # ....
    return res


def print_title(filename):
    title = get_title(filename)
    if title:
        print('        <div class="title">', title,  '</div>')


# ----------------------------------------------------------------------
def prettify_file(filename):
    print('     <div class="main">')
    print_title(filename)
    
    # Afficher le fichier 
    f = open(filename, 'r')
    for line in f:
        print(pretty(line), end='')
    close(f)
    print('     </div>')

# ----------------------------------------------------------------------
def produce_page(filename):
    print_html_begin('Fichier: ' + filename)
    prettify_file(filename)
    print_html_end()

# ----------------------------------------------------------------------
def main():
    if (len(argv) != 2):
        fatal_error('Usage: ' + argv[0] + ' file')
    produce_page(argv[1])

# Appeler la fonction main
main()

