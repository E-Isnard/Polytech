#! /usr/bin/python3
from compressionImage import *
import argparse

parser = argparse.ArgumentParser(
    description="Compression d'image utilisant la dct")

parser.add_argument('ifile', help="chemin de l'image de depart")
parser.add_argument('ofile', help="chemin de l'image comprimee")
parser.add_argument('-f', '--fourier', action="store_true",
                    help="Option qui permet de sauvegarder le spectre apres compression de l'image")
parser.add_argument('-i', '--info', action="store_true",
                    help="Option qui permet d'afficher le taux de compression de l'image,l'erreur en norme L2 et la matrice Q utilisee")
parser.add_argument('-q', '--quantization', help="Permet de choisir le matrice de quantification,par défaut on utilise la matrice de jpg et les options de 1 à 8 mettent Q avec que des 255 sauf le bloc en haut à gauche de la taille choisie qui contient des 1.", default=0, type=int)

args = parser.parse_args()


#On construit la matrice Q
if(args.quantization >= 1 and args.quantization <= 8):
    i = args.quantization
    Q = np.ones((8, 8))*1000
    Q[0:i,0:i] = 1
else:
    #Par défaut on utilise la matrice de jpg
    Q = np.matrix('16 11 10 16 24 40 51 61;12 12 13 19 26 58 60 55;14 13 16 24 40 57 69 56; 14 17 22 29 51 87 80 62;18 22 37 56 68 109 103 77;24 35 55 64 81 104 113 92;49 64 78 87 103 121 120 101;72 92 95 98 112 100 103 99')

    

#On compresse l'image choisie
res = compressionImg(args.ifile,Q)
#On la sauvegarde a l'endroit choisi
res[0].save(args.ofile)

#Si on a mis l'option -f on sauvegarde le spectre de l'image au meme endroit que l'image compresee avec "Fourier" avant l'extension
if args.fourier:
    imgCompressFourier = fourierImg(args.ifile, Q)[0]
    split = args.ofile.split(".")
    split[-2] += "Fourier"
    outPutFourier = "."
    outPutFourier = outPutFourier.join(split)
    # print(outPutFourier)
    imgCompressFourier.save(outPutFourier)

#On affiche les informations si on a mis l'option -i
if args.info:
    print("Difference en norme L2")
    print(res[2])
    print("Taux de compression de l'image")
    print(res[3])
    print("Matrice de quantification utilisee")
    print(Q)
