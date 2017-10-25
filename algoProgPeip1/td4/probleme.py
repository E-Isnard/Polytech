# -*- coding: utf-8 -*-
#!/usr/bin/env python3


def nbjour(mois,annee):
    if mois == 2:
            return (29 if (annee%4 == 0 and annee%100 != 0) or (annee%400==0) else 28)
    elif mois%2 == 0:
        if mois < 8:
                return 30
        else:
                return 31 
    else:
        if mois < 8:
                return 31
         
        else:
                return 30
         

def checkdate(jour,mois,annee):
    if annee < 1600 or annee>2018:
        return 3
    elif mois > 12 or mois < 1:
        return 2
    elif jour<0 or jour>nbjour(mois,annee):
        return 1
    else:
        return 0



def lendemain(jour,mois,annee):
        check = checkdate(jour,mois,annee)
        if check == 3:
                print("probleme avec l'année")
        elif check == 2:
                print("probleme avec le mois")
        elif check == 1:
                print("probleme avec le jour")
        else:
                jour += +1
                if jour > nbjour(mois,annee):
                        jour = 1
                        mois += 1
                        if mois>12:
                                mois=1
                                annee += 1
        
                return ("{}/{}/{}".format(jour,mois,annee))

tarrif = int(input("Tarrif: "))
date_arrivee = input("DATE ARRIVEE:")
date_depart = input("DATE DEPART: ")

i = 0
while date_arrivee != date_depart:
    i += 1
    d_arrivee,m_arrivee,y_arrivee = date_arrivee.split("/")
    date_arrivee = lendemain(int(d_arrivee),int(m_arrivee),int(y_arrivee))

print("Vous etes resté {} jours".format(i))
print("Vous devez {} euros".format(i*tarrif))




                
                        
    
    
