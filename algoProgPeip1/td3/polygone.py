from turtle import *

l =int(input("Longueur des cotes ?: "))
n = int(input("Combien de cote ?: "))
def polygone(n,l):
        for i in range(n):
                forward(l)
                left(360/n)


polygone(n,l)
up()
forward(0.5*l)
left(45)
down()
l = math.sqrt(((1-f)*l+math.sqrt((l*f)**2)+(l*f*math.sin(2*math.pi/n)**2)**2)+(l*f*math.sin(2*math.pi/n))**2)
forward(l)
