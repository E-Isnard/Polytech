"""Ensemble de fonctions permettant de faire de la compression d'image avec la DCT(Discrete Cosine Transform) """

from PIL import Image
import numpy as np
from numpy.linalg import norm

#Construction de la matrice P permettant de calculer la DCT de l'image
P = np.zeros((8, 8))
for i in range(8):
    for j in range(8):
        if i == 0:
            P[i, j] = 1/np.sqrt(8)
        else:
            P[i, j] = np.sqrt(1/4)*np.cos((np.pi*(2*j+1)*i)/16)


del i
del j


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


def tauxCompression(MCompress, M):
    """
    Calcule le taux de compression entre MCompress et M
    """
    nonZeroCompress = np.count_nonzero(MCompress)
    nonZero = np.count_nonzero(M)

    return 100-(nonZeroCompress/nonZero)*100


def searchBloc(M, i, j):
    """
    Extrait la sous matrice 8x8 d'indice i,j.
    """
    return M[8*i:8*(i+1), 8*j:8*(j+1)]


def remplaceBloc(M, bloc, i, j):
    """
    Remplace le bloc de (i,j) de la matrice M par bloc
    """

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


def compression(M,Q):
    """
    Calcule le spectre de la matrice M et filtre les hautes frequences
    """

    D = np.zeros(M.shape)

    nb_blocsL = M.shape[0]//8
    nb_blocsC = M.shape[1]//8

    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = searchBloc(M, i, j)
            blocFourier = DCT(bloc)

            blocFourier = (np.divide(blocFourier, Q))
            blocFourier = blocFourier.astype(int)

            remplaceBloc(D, blocFourier, i, j)

    return D


def compdecompression(M,Q):
    """
    Combine compression et decompression(pas utilisee)
    """
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
            remplaceBloc(D, invDCT(bloc), i, j)

    return D+np.ones(D.shape)*128


def decompression(M,Q):
    """
    Decompresse une matrice M
    """

    D = np.zeros(M.shape)

    nb_blocsL = M.shape[0]//8
    nb_blocsC = M.shape[1]//8

    for i in range(nb_blocsL):
        for j in range(nb_blocsC):
            bloc = np.multiply(searchBloc(M, i, j), Q)

            remplaceBloc(D, invDCT(bloc), i, j)

    return D+np.ones(D.shape)*128


def compressionImg(nomImage,Q):
    """
    Compresse une image en calculant sa dct et en filtrant les frequences avec la matrice Q
    """
    # Initialisation
    img = Image.open(nomImage)
    img = tronquer(img)

    imgBaseMatrix = np.asarray(img)

    # On recupère les 3 canneaux
    r, g, b = decompRGB(imgBaseMatrix)

    imgMatrix = np.copy(imgBaseMatrix)

    # imgMatrix[:, :, 0] = compdecompression(r)
    # imgMatrix[:, :, 1] = compdecompression(g)
    # imgMatrix[:, :, 2] = compdecompression(b)

    # On calcule les spectres filtrés des canneaux
    rCompress = compression(r,Q)
    gCompress = compression(g,Q)
    bCompress = compression(b,Q)

    # On calcule leurs taux de compression
    tauxCompressionR = tauxCompression(rCompress, r)
    tauxCompressionG = tauxCompression(gCompress, g)
    tauxCompressionB = tauxCompression(bCompress, b)

    # On fait la moyenne des taux de compression des 3 canneaux
    tauxCompressionTotal = (
        tauxCompressionR+tauxCompressionG+tauxCompressionB)/3

    # On affecte à la matrice de sortie les canneaux comprimes
    imgMatrix[:, :, 0] = decompression(rCompress,Q)
    imgMatrix[:, :, 1] = decompression(gCompress,Q)
    imgMatrix[:, :, 2] = decompression(bCompress,Q)

    # On gere les eventuelles valeurs qui ne seraient pas entre 0 et 255(erreurs d'arrondi)
    imgMatrix = np.maximum(imgMatrix, 0)
    imgMatrix = np.minimum(imgMatrix, 255)

    # On convertit la matrice en image
    imgCompress = Image.fromarray(imgMatrix)

    # On renvoie l'image comprimee,sa matrice,l'erreur en norme L2 et le taux de compression de l'image(arrondi au dixieme)
    return imgCompress, imgMatrix, norm(imgBaseMatrix-imgMatrix)/norm(imgBaseMatrix), np.floor((tauxCompressionTotal*100))/100


def fourierImg(nomImage,Q):
    """
    Calcule le spectre filtré d'une image
    """
    # Initialisation
    img = Image.open(nomImage)
    img = tronquer(img)
    imgBaseMatrix = np.asarray(img)

    # On recupère les 3 canneaux
    r, g, b = decompRGB(imgBaseMatrix)

    # On copie la matrice de base(bizarrement si on initialise la matrice avec des 0 on obtient des valeurs non entières)
    imgMatrixF = np.copy(imgBaseMatrix)

    # On affecte a chaque cannal de la matrice les canneaux comprimés
    imgMatrixF[:, :, 0] = compression(r,Q)
    imgMatrixF[:, :, 1] = compression(g,Q)
    imgMatrixF[:, :, 2] = compression(b,Q)

    # On gère les eventuelles valeurs qui ne seraient pas entre 0 et 255 (erreurs d'arrondi)
    imgMatrixF = np.maximum(imgMatrixF, 0)
    imgMatrixF = np.minimum(imgMatrixF, 255)

    # On convertit la matrice en Image
    imgCompressF = Image.fromarray(imgMatrixF)

    return imgCompressF, imgMatrixF


if __name__ == '__main__':
    Q = np.matrix('16 11 10 16 24 40 51 61;12 12 13 19 26 58 60 55;14 13 16 24 40 57 69 56; 14 17 22 29 51 87 80 62;18 22 37 56 68 109 103 77;24 35 55 64 81 104 113 92;49 64 78 87 103 121 120 101;72 92 95 98 112 100 103 99')
    # Q=np.ones((8,8))*255
    # Q[0,0]=1

    res = compressionImg("Images\\arouf.jpg",Q)

    res[0].save("ImageCompressee.jpg")
    print("Difference en norme L2")
    print(res[2])
    print("Taux de compression de l'image")
    print(res[3])
    fourierImg("Images\\arouf.jpg",Q)[0].save("ImageFourier.jpg")
