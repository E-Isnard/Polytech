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

def drawGrid():
    c = 0
    l = 0
    for i in range(3):
        for j in range(3):
            drawSquare(-200+l,-200+c)
            l += 150
        l = 0
        c += 150



drawGrid()
mainloop()
