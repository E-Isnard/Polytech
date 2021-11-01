from operator import ne
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans,MeanShift,estimate_bandwidth,OPTICS,Birch
from matplotlib import cm
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.colors as col

data = np.genfromtxt("data_tpmam5inum.csv",delimiter=",")
x = data[:,0]
y = data[:,1]
z = data[:,2]
T = data[:,3]
S = data[:,6]
cmap2= col.LinearSegmentedColormap.from_list('own2', ["purple","blue","cyan","green","yellow","red"])


def plot_clusters(method,x,y,z,T,S,plot_init_data=True,info=True,**kwargs):

    if plot_init_data:
        fig = plt.figure()
        ax = fig.add_subplot(projection='3d')
        p = ax.scatter(x,y,z,c=T,s=S*75,cmap=cmap2)
        fig.colorbar(p,ax=ax)
        plt.title("Scatter plot with temperatures in Kelvin")
        plt.show()

    X = data[:,0:4]
    clusts = method(**kwargs).fit(X)
    name = clusts.__class__.__name__
    if info:
        print(name.upper()+" CLUSTERS:")
    k = np.max(clusts.labels_)+1
    new_X = []
    for ki in range(k):
        mask = (clusts.labels_ == ki)
        area = np.sum(S[mask])
        temp = np.round(np.mean(T[mask]),2)
        sigma_temp = np.round(np.std(T[mask]),2)
        bary_x = np.round(np.mean(x[mask]),2)
        bary_y = np.round(np.mean(y[mask]),2)
        bary_z = np.round(np.mean(z[mask]),2)
        bary = (bary_x,bary_y,bary_z)
        new_X.append([bary_x,bary_y,bary_z,temp,area])
        if info:
            print(f"Area of cluster {ki}: {area}")
            print(f"Temperature of cluster {ki}: {temp}")
            print(f"Standard deviation of temperature in cluster {ki}: {sigma_temp}")
            print(f"Coordinates of barycenter of cluster {ki}: {bary}\n")

    new_X = np.array(new_X)
    x = new_X[:,0]
    y = new_X[:,1]
    z = new_X[:,2]
    T = new_X[:,3]
    S = new_X[:,4]
    fig = plt.figure()
    ax = fig.add_subplot(projection='3d')
    p = ax.scatter(x,y,z,c=T,s=S*75,cmap=cmap2)
    fig.colorbar(p,ax=ax)
    plt.title("Scatter plot of clusters with "+clusts.__class__.__name__)
    plt.show()

plot_clusters(KMeans,x,y,z,T,S,n_clusters=800)
plot_clusters(Birch,x,y,z,T,S,plot_init_data=False,n_clusters=800)
