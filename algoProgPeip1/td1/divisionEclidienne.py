def divEuclidienne():
    a = int(input("Valeur de a:"))
    b = int(input("Valeur de b:"))
    q = a//b
    r  = a%b
    print("Division euclidienne de {} par {}!".format(a,b))
    print(" {} = {} * {} + {}".format(a,b,q,r))

divEuclidienne()
