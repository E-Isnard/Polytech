from turtle import *
from math import *
import time
hideturtle()
player = 1
speed("fastest")
def drawSquare(x,y,l):
        up()
        goto(x,y)
        down()
        for i in range(4):
                forward(l)
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
                        drawSquare(-200+l,-200+c,150)
                        l += 150
                l = 0
                c += 150
        

def player1(x,y):
        global player
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
    
        player = 2

def player2(x,y):
        global player
        seth(45)    
        if -200<x<-50 and 100<y<250:
                drawSquare(-125,100,sqrt(2*75**2))
        if -50<x<100 and 100<y<250:
                drawSquare(25,100,sqrt(2*75**2))
        if 100<x<250 and 100<y<250:
                drawSquare(175,100,sqrt(2*75**2))

        if -200<x<-50 and -50<y<100:
                drawSquare(-125,-50,sqrt(2*75**2))
        if -50<x<100 and -50<y<100:
                drawSquare(25,-50,sqrt(2*75**2))
        if 100<x<250 and -50<y<100:
                drawSquare(175,-50,sqrt(2*75**2))

        if -200<x<-50 and -200<y<-50:
                drawSquare(-125,-200,sqrt(2*75**2))
        if -50<x<100 and -200<y<-50:
                drawSquare(25,-200,sqrt(2*75**2))
        if 100<x<250 and -200<y<-50:
                drawSquare(175,-200,sqrt(2*75**2))   
        seth(0)
        player = 1

def turn(x,y):
        if player == 1:
                player1(x,y)
        elif player == 2:
                player2(x,y)

drawGrid()

onscreenclick(turn)
mainloop()
