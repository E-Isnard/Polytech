from turtle import *
from math import *
import time

nbPoly = int(input("Combien de polygones ? "))
n = int(input("Combien de cotes ? "))
l = int(input("Longueur du 1er polygone: "))
f = float(input("facteur: "))

BG = l*f
FB = (1-f)*l
GK = sin(2*pi/n)*l*f
BK = sqrt((BG**2)-(GK**2))
FK = FB + BK
a = atan(GK/FK)
FG = sqrt((GK**2)+(FK**2))
print("a = ",degrees(a))
print("FG = ",FG)
print("BK = ",BK)
print("BG = ",BG)
print("GK = ",GK)

def polygone(n,l):
        for i in range(n):
                forward(l)
                left(360/n)
up()
goto(-100,-300)
down()
for i in range(nbPoly):
    
    polygone(n,l)
    up()
    forward(l*f)
    l = sqrt(((sin(2*pi/n)*l*f)**2)+((((1-f)*l)+(sqrt(((l*f)**2)-(sin(2*pi/n)*l*f)**2)))**2))
    left(degrees(a))
    down()


time.sleep(3)
