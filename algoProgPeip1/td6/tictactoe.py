from turtle import *
import time
hideturtle()
speed("fastest")
def drawSquare(x,y):
    up()
    goto(x,y)
    down()
    for i in range(4):
        forward(150)
        left(90)

def drawCircle(x,y):
    up()
    goto(x,y)
    down()
    circle(75)

def drawGrid():
    c = 0
    l = 0
    for i in range(3):
        for j in range(3):
            drawSquare(-200+l,-200+c)
            l += 150
        l = 0
        c += 150
        

def play(x,y):

    if -200<x<-50 and 100<y<250:
            drawCircle(-125,100)
    if -50<x<100 and 100<y<250:
            drawCircle(25,100)
    if 100<x<250 and 100<y<250:
            drawCircle(175,100)

    if -200<x<-50 and -50<y<100:
            drawCircle(-125,-50)
    if -50<x<100 and -50<y<100:
            drawCircle(25,-50)
    if 100<x<250 and -50<y<100:
            drawCircle(175,-50)

    if -200<x<-50 and -200<y<-50:
            drawCircle(-125,-200)
    if -50<x<100 and -200<y<-50:
            drawCircle(25,-200)
    if 100<x<250 and -200<y<-50:
            drawCircle(175,-200)

drawGrid()
onscreenclick(play)
mainloop()
