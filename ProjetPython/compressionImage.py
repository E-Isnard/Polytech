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
    
    return (nonZeroCompress/nonZero)*100

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
    return np.matmul(P.T, np.matmul(M, P))


def decompRGB(img):
    """
    Decompose une image en 3 tableaux contenant les valeurs rgb de chaque pixel.
    Decale aussi les valeurs des pixels pour qu'elles soient entre -128 et 127.
    """

    imMatrix = np.asarray(img)

    centreMatrix = 128*np.ones(imMatrix.shape)
    imMatrix = imMatrix-centreMatrix

    rMatrix = imMatrix[:, :, 0]
    gMatrix = imMatrix[:, :, 1]
    bMatrix = imMatrix[:, :, 2]

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

    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = searchBloc(M, i, j)
            bloc = DCT(bloc)
            bloc = (np.divide(bloc, Q))
            bloc = bloc.astype(int)
             
            bloc = np.multiply(bloc, Q)
            replaceBloc(D, invDCT(bloc), i, j)
            
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

    r, g, b = decompRGB(img)

    
    imMatrix = np.copy(imgBase)
    

    imMatrix[:, :, 0] = compdecompression(r)
    imMatrix[:, :, 1] = compdecompression(g)
    imMatrix[:, :, 2] = compdecompression(b)

    




    imMatrix = np.maximum(imMatrix, 0)
    imMatrix = np.minimum(imMatrix, 255)

    
    imgCompress = Image.fromarray(imMatrix)

    return imgCompress,imMatrix,norm(imMatrix-imgBase)


def fourierImg(nomImage):
    # Initialisation
    img = Image.open(nomImage)
    img = tronquer(img)

    r, g, b = decompRGB(img)

    imMatrix = np.asarray(img)
    imMatrix = imMatrix.copy()

    imMatrix[:, :, 0] = compression(r)
    imMatrix[:, :, 1] = compression(g)
    imMatrix[:, :, 2] = compression(b)

    imMatrix = np.maximum(imMatrix, 0)
    imMatrix = np.minimum(imMatrix, 255)

    imgCompressF = Image.fromarray(imMatrix)

    return imgCompressF,imMatrix


res = compressionImg("Images\\Image.png")

res[0].save("ImageCompressee.png")
print(res[2])
fourierImg("Images\\Image.png")[0].save("ImageFourier.png")



