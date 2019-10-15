#                                       -*-coding: utf-8 -*-
#
# peip3.py      -- Un module pour simplifier la vie des étudiants PeiP
#
#           Author: Erick Gallesio [eg@unice.fr]
#    Creation date: 22-Aug-2013 09:07 (eg)
# Last file update:  6-Feb-2018 18:33 (eg)
#


save_open=open

# import sys, os, glob;
from sys     import *
from os      import system
from os      import *
from os.path import *
from glob    import *
from math    import *
from time    import asctime

import os; system = os.system; unlink = os.unlink

# ----------------------------------------------------------------------
# fatal_error
# ----------------------------------------------------------------------
def fatal_error(*args):
    """
    affiche toutes les chaînes qui lui sont passés en paramètre sur
    la sortie standard d'erreur et stoppe le programe.
    """
    stderr.write(''.join(args) + '\n')
    exit(1);

# ----------------------------------------------------------------------
# Un printf à la C pour ne pas montrer l'opérateur "%"
# qui perturbe les étudiants (mais pourquoi donc?)
# ----------------------------------------------------------------------
def printf(format, *args):
    stdout.write(str(format) % args)

def eprintf(format, *args):
    stderr.write(str(format) % args)

def sprintf(format, *args):
    return str(format) % args

def fprintf(f, format, *args):
    f.write(str(format) % args)


# ----------------------------------------------------------------------
# get_options: retourne les options et les paramètres de argv
#    >>> o,p=get_options('foo --foo=bar --gee a b c'.split())
#    >>> o
#    [['--foo', 'bar'], ['--gee', '']]
#    >>> p
#    ['a', 'b', 'c']
#----------------------------------------------------------------------
def get_options(argv):
    o =[];
    i = 1;
    for opt in argv[1:] :
        if not opt[0] == '-' :
            break
        res = opt.split('=');
        if len(res) == 1:
            res.append('');
        o.append(res);
        i += 1;
    return o, argv[i:]


def parse_arguments(template):
    i = 1;
    for opt in argv[1:] :
        if not opt[0] == '-' or opt == '--':
            break
        res = opt.split('=');
        if len(res) == 1: res.append('');

        if res[0] in template:
            template[res[0]](res[1])
        else:
            stderr.write("**** Option '" + res[0] + "' inconnue\n")
        i += 1;
    return argv[i:]


# ----------------------------------------------------------------------
# urlopen -- Ouverture d'un URL
# On se fait passer pour Mozilla sinon certains sites renvoie une
# erreur 403 (e.g. Google ou Wikipedia)
# ----------------------------------------------------------------------
import urllib.request, urllib.parse

# def urlopen(url):
#     agent = 'Mozilla/5.0 (X11; Linux x86_64; rv:35.0) Gecko/20100101 Firefox/35.0'
#     content= 'application/x-www-form-urlencoded;charset=utf-8'
#
#     req = urllib.request.Request(url, headers={ 'User-Agent' : agent,
#                                                 'Content-Type' : content})
#
#     return urllib.request.urlopen(req)


class urlopen:
    """Une classe pour accéder au contenu de pages web.
Le nom choisi laisse croire que c'est une fonction (en Python 2.0,
c'était le cas). La classe gère le fait que le résultat n'est plus une
string mais un bytearray, ce qui compliquerait la vie des étudiants)
    """

    def __init__(self, url):
        agent = 'Mozilla/5.0 (X11; Linux x86_64; rv:35.0) Gecko/20100101 Firefox/35.0'
        content= 'application/x-www-form-urlencoded;charset=utf-8'
        req = urllib.request.Request(url, headers={'User-Agent' : agent,
                                                   'Content-Type' : content})
        self.file = urllib.request.urlopen(req)

    def __iter__(self):
        return self

    def __next__(self):
        res = self.file.readline()
        if res == None or res == b'' : raise StopIteration
        return res.decode()

    # On peut aussi utiliser une méthode __iter__ (sans __next__ du coup) qui
    # est un générateur. Cela donne:
    #    def __iter__(self):
    #        res = True
    #        while res != None and res != b'' :
    #            res = self.file.readline()
    #            yield res.decode()
    #



# ----------------------------------------------------------------------
# REGEXPs
# ----------------------------------------------------------------------
import re

regsub = re.sub

def regsearch(rgx, line):
    return re.search(rgx, line) != None

# ======================================================================
#
# Support pour le Td sur les images
#
# ======================================================================


#
# Une classe pour représenter des pixels
#
class Pixel:
    "Un pixel RVB."
    def __init__(self, R =0, G =0, B =0):
        self.R = R
        self.G = G
        self.B = B

    def __eq__(self, x):
        return (self.R == x.R) and (self.G == x.G) and (self.B == x.B)

    def __repr__(self):
        return "{Pix %d,%d,%d}" % (self.R, self.G, self.B)

#
# Une classe pour représenter des images
#
class Image:
    "Une image."
    def __init__(self, width, height, depth, pixels):
        self.width  = width
        self.height = height
        self.depth  = depth
        self.pixels = pixels

    def __repr__(self):
        return "{Img %dx%d (%d) => %s}" % (self.width, self.height, self.depth,
                                           self.pixels)

# Le lecteur d'image PPM. Il renvoit 4 valeurs: largeur, hauteur, prof., pixels
# où pixel est un tableau de largeur x hauteur entiers compris entre 0 et 255
def readPPM(filename):
    if (filename):
        f = open(filename, "r")
    else:
        f = stdin

    res = []
    for line in f:
        if (line[0] != "#"):
            if line.strip() == "P3" :
                res.append("P3")
            else:
                # Ajouter tous les items de la ligne après conversion en int
                for i in line.split():
                    res.append(int(i))
    if (filename):
        f.close()
    return res[1],res[2],res[3], res[4:]

#
# Read_image:
#
def read_image(filename = False):
    """Renvoie l'image correspondant au fichier passé en paramètre.
       Si filename = False, la lecture se fait sur le fichier standard d'entrée"""

    width, height, depth, data=readPPM(filename)
    # Convertir data en un tableau de pixels
    pix = []
    for i in range(0, len(data), 3):
        pix.append(Pixel(data[i], data[i+1], data[i+2]))
    return Image(width, height, depth, pix)


# ======================================================================
#
# Support pour les Tds Web (serveur, wiki, ...)
#
# ======================================================================



# ----------------------------------------------------------------------
# La classe Server qui permet de construire un serveur sans connaitre
# les arcanes des sockets
# ----------------------------------------------------------------------
import socket

class Server:
    '''
    Classe décrivant un Serveur TCP.

    La version qui est donnée ici ne pourrait pas marcher dans un
    environnement multi-threads, ce qui est largement au dessus de ce
    dont on a besoin pour le cours de CiP2
    '''

    def __init__(self, port = 1234, verbose = False):
        c = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        c.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        c.bind(("localhost", port))
        c.listen(5)
        self.connection = c
        self.port       = port
        self.verbose    = verbose

    def accept_connection(self):
        if (self.verbose):
            print("-----------------------------------")
            print("Serveur en attente connexion sur le port %d" % self.port)

        # Let the wizard play
        csock, caddr = self.connection.accept()
        cfile = csock.makefile('rw', 0)
        self.csock = csock
        self.caddr = caddr
        self.cfile = cfile

        if (self.verbose):
            print("  Connexion client depuis la machine %s" % str(caddr))
        return cfile

    def readline(self):
       line = self.cfile.readline().strip()
       if (self.verbose):
          print("    <== %s" % line)
       return line

    def writeline(self, string):
       if (self.verbose):
          print("    ==> %s" % string)
       self.cfile.write(string)

    def close(self):
       if (self.verbose):
          print("  Fermeture de la connexion avec %s" % str(self.caddr))
       self.cfile.flush()
       self.cfile.close()
       self.csock.shutdown(socket.SHUT_RDWR)

# Open est écrasé par notre manip. On corrige
open=save_open

# Pour éviter la notation pointée le plus possible, on définit une fonction close
def close(f):
    f.close()

# Même chose pour un glob qui trie son résultat
def direntries(pattern):
    files = glob(pattern)
    files.sort()
    return files

# La fonction reverse(lst)
def reverse(lst):
    return list(reversed(lst))

# La fonction sort(lst)
def sort(lst):
    lst = lst.copy()
    lst.sort()
    return lst

#
# Juste au cas où
#
if __name__ == "__main__" :
    fatal_error('Il faut faire "import peip3" et non pas exécuter ce fichier');
