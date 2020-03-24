#!/bin/bash

for file in $(ls $1/*)
do
date=$(exif -t 0x0132 $file | grep "Value" | cut -d " " -f4 | tr ":" "-" | tr -d " ")
annee=$(echo $date | cut -d "-" -f1)
heure=$(exif -t 0x0132 $file | grep "Value" | cut -d " " -f5 | tr ":" "-")
mkdir -p $2/$annee

if [ -e $2/$annee/$date"_"$heure".jpg" ] 
then
    #echo "Erreur le fichier exite déjà">&2
    choix=$(ma_funct.sh $2/$annee/$date"_"$heure".jpg")
    echo $choix
    if [ $choix -eq 0 ]
    then
        cp $file $2/$annee/$date"_"$heure".jpg"
    fi
else
    cp $file $2/$annee/$date"_"$heure".jpg"
fi
done

#echo La photo du fichier $1 a été prise en $annee \(précisement le $date\) à $heure

