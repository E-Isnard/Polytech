from compressionImage import *
import argparse

parser = argparse.ArgumentParser(description='Compression d image utilisant la dct')

parser.add_argument('ifile',help='chemin de l image de depart')
parser.add_argument('ofile',help='chemin de l image comprimee')
parser.add_argument('-f','--fourier',action="store_true",help="Option qui permet de sauvegarder le spectre apres compression de l'image")
parser.add_argument('-i','--info',action="store_true",help="Option qui permet d'afficher le taux de compression de l'image et l'erreur en norme L2")


args = parser.parse_args()

res = compressionImg(args.ifile)
res[0].save(args.ofile)

if args.fourier:
    imgCompressFourier = fourierImg(args.ifile)[0]
    split = args.ofile.split(".")
    split[-2] += "Fourier"
    outPutFourier = "."
    outPutFourier = outPutFourier.join(split)
    # print(outPutFourier)
    imgCompressFourier.save(outPutFourier)

if args.info:
    print("Difference en norme L2")
    print(res[2])
    print("Taux de compression de l'image")
    print(res[3])

