from compressionImage import *
import argparse

parser = argparse.ArgumentParser(description='Compression d image utilisant la dct')

parser.add_argument('ifile',help='chemin de l image de depart')
parser.add_argument('ofile',help='chemin de l image comprimee')
parser.add_argument('-f','--fourier',action="store_true")

args = parser.parse_args()

imgCompress = compressionImg(args.ifile)
imgCompress.save(args.ofile)

if args.fourier:
    imgCompressFourier = fourierImg(args.ifile)
    split = args.ofile.split(".")
    split[-2] += "Fourier"
    outPutFourier = "."
    outPutFourier = outPutFourier.join(split)
    # print(outPutFourier)
    imgCompressFourier.save(outPutFourier)

