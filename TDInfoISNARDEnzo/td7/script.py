#!/usr/bin/python3
from peip3 import *

def save_image(img,filename):
        f = open(filename,"w")
        print("P3",file=f)
        print(img.width,img.height,img.depth,file=f)
        for pixel in img.pixels:
            pixel = sepia(pixel) 
            print(pixel.R,file=f)
            print(pixel.G,file=f)
            print(pixel.B,file=f)
        f.close()


def copy_image(src, dst):
    img = read_image(src)
    save_image(img, dst)

def chooseColor(string):
    if string == "bleu":
        return Pixel(0,0,255)
    elif string == "vert":
        return Pixel(50, 145, 36)
    elif string == "orange":
        return Pixel(249,122,34)

def swapcolor(pixel,color1,color2):
        if pixel == color1:
                return color2
        
        return pixel

def negatif(pixel):
        r = 255 - pixel.R
        g = 255 - pixel.G
        b = 255 - pixel.B
        return Pixel(r,g,b)


def niveauGris(pixel):
        gris = (pixel.R + pixel.G + pixel.B)/3
        gris = int(gris)
        return Pixel(gris,gris,gris)

def sepia(pixel):
        r = int(pixel.R * 0.393 + pixel.G * 0.769 + pixel.B * 0.189)
        g = int(pixel.R * 0.349 + pixel.G * 0.686 + pixel.B * 0.168)
        b = int(pixel.R * 0.272 + pixel.G * 0.534 + pixel.B * 0.131)
        return Pixel(r,g,b)

def main():
    if (len(argv) == 3):
        # On a une source et une destination
        copy_image(argv[1],argv[2])



main()  
