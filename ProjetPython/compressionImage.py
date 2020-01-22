from PIL import Image
import numpy as np
from numpy.linalg import norm


P = np.zeros((8, 8))
for i in range(8):
    for j in range(8):
        if i == 0:
            P[i, j] = 1/np.sqrt(8)
        else:
            P[i, j] = np.sqrt(1/4)*np.cos((np.pi*(2*j+1)*i)/16)


Q = np.matrix('16 11 10 16 24 40 51 61;12 12 13 19 26 58 60 55;14 13 16 24 40 57 69 56; 14 17 22 29 51 87 80 62;18 22 37 56 68 109 103 77;24 35 55 64 81 104 113 92;49 64 78 87 103 121 120 101;72 92 95 98 112 100 103 99')


def tronquer(image):
    """
    Tronque une image pour que ses dimensions soient des multiples de 8.
    """

    width, height = image.size
    newHeight = height - height % 8
    newWidth = width - width % 8
    area = (0, 0, newWidth, newHeight)
    image = image.crop(area)
        
    return image

def tauxCompression(MCompress,M):
    nonZeroCompress = np.count_nonzero(MCompress)
    nonZero = np.count_nonzero(M)
    
    return 100-(nonZeroCompress/nonZero)*100

def searchBloc(M, i, j):
    """
    Extrait la sous matrice 8x8 d'indice i,j.
    """
    return M[8*i:8*(i+1), 8*j:8*(j+1)]


def replaceBloc(M, bloc, i, j):

    M[8*i:8*(i+1), 8*j:8*(j+1)] = bloc


def DCT(M):
    """
    Changement de base a l'aide de la transformee de Fourier.
    """

    return np.dot(P, np.dot(M, P.T))


def invDCT(M):
    """
    Changement de base inverse a l'aide de la transformee de Fourier.
    """
    return np.dot(P.T, np.dot(M, P))


def decompRGB(imgMatrix):
    """
    Decompose un tableau en 3 tableaux contenant les valeurs rgb de chaque pixel.
    Decale aussi les valeurs des pixels pour qu'elles soient entre -128 et 127.
    """

    

    centreMatrix = 128*np.ones(imgMatrix.shape)
    imgMatrix = imgMatrix-centreMatrix

    rMatrix = imgMatrix[:, :, 0]
    gMatrix = imgMatrix[:, :, 1]
    bMatrix = imgMatrix[:, :, 2]

    return rMatrix, gMatrix, bMatrix


def compression(M):

    D = np.zeros(M.shape)

    nb_blocsL = M.shape[0]//8
    nb_blocsC = M.shape[1]//8

    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = searchBloc(M, i, j)
            blocFourier = DCT(bloc)

            blocFourier = (np.divide(blocFourier, Q))
            blocFourier = blocFourier.astype(int)

            replaceBloc(D, blocFourier, i, j)

    return D

def compdecompression(M):
    
    D = np.zeros(M.shape)

    nb_blocsL = M.shape[0]//8
    nb_blocsC = M.shape[1]//8
    tauxCompression = 0
    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = searchBloc(M, i, j)
            bloc = DCT(bloc)
            bloc = (np.divide(bloc, Q))
            bloc = bloc.astype(int)
            
             
            bloc = np.multiply(bloc, Q)
            replaceBloc(D, invDCT(bloc), i, j)
    
    tauxCompression/=(nb_blocsC*nb_blocsL)
    return D+np.ones(D.shape)*128
    
def decompression(M):

    D = np.zeros(M.shape)

    nb_blocsL = M.shape[0]//8
    nb_blocsC = M.shape[1]//8

    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = np.multiply(searchBloc(M, i, j), Q)

            replaceBloc(D, invDCT(bloc), i, j)

    return D+np.ones(D.shape)*128


def compressionImg(nomImage):
    # Initialisation
    img = Image.open(nomImage)
    img = tronquer(img)

    imgBase = np.asarray(img)
    

    r, g, b = decompRGB(imgBase)
    
    
    imgMatrix = np.copy(imgBase)
    

    # imgMatrix[:, :, 0] = compdecompression(r)
    # imgMatrix[:, :, 1] = compdecompression(g)
    # imgMatrix[:, :, 2] = compdecompression(b)

    rCompress = compression(r)
    gCompress = compression(g)
    bCompress = compression(b)

    tauxCompressionR = tauxCompression(rCompress,r)
    tauxCompressionG = tauxCompression(gCompress,g)
    tauxCompressionB = tauxCompression(bCompress,b)

    tauxCompressionTotal = (tauxCompressionR+tauxCompressionG+tauxCompressionB)/3

    imgMatrix[:,:,0] = decompression(rCompress)
    imgMatrix[:,:,1] = decompression(gCompress)
    imgMatrix[:,:,2] = decompression(bCompress)


    imgMatrix = np.maximum(imgMatrix, 0)
    imgMatrix = np.minimum(imgMatrix, 255)

    
    imgCompress = Image.fromarray(imgMatrix)

    return imgCompress,imgMatrix,norm(imgBase-imgMatrix)/norm(imgBase),np.floor((tauxCompressionTotal*100))/100


def fourierImg(nomImage):
    # Initialisation
    img = Image.open(nomImage)
    img = tronquer(img)

    imgBase = np.asarray(img)

    r, g, b = decompRGB(imgBase)

    
    imgMatrixF = np.copy(imgBase)

    imgMatrixF[:, :, 0] = compression(r)
    imgMatrixF[:, :, 1] = compression(g)
    imgMatrixF[:, :, 2] = compression(b)

    imgMatrixF = np.maximum(imgMatrixF, 0)
    imgMatrixF = np.minimum(imgMatrixF, 255)

    imgCompressF = Image.fromarray(imgMatrixF)

    return imgCompressF,imgMatrixF



if __name__ == '__main__':
    res = compressionImg("Images\\arouf.jpg")

    res[0].save("ImageCompressee.jpg")
    print("Difference en norme L2")
    print(res[2])
    print("Taux de compression de l'image")
    print(res[3])
    fourierImg("Images\\arouf.jpg")[0].save("ImageFourier.jpg")



