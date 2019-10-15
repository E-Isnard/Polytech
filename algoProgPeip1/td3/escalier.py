from turtle import *
import time

t = int(input("Quelle doit etre la taille des marches ?"))
h = (-window_height()/2)+50
l = (window_width()/2)-50
print(l,h)
up()
goto(l,h)
write("Depart")
left(180)
down()

while h < (window_height()/2)-80 or l > (-window_width()/2)+80:
    forward(t)
    right(90)
    forward(t)
    left(90)
    h += t
    l -= t

print(l,h)
print(-window_width()/2,window_height()/2)
up()
goto(((window_width()/2)-50),((-window_height()/2)+100))
if h > -window_height()/2 or l < window_width()/2:
    write("arrive")
else:
    write("tronqué")
time.sleep(3)
