#!/bin/bash

for fich in $@
do

    read -p "Voulez-vous remplez $fich ? (o/n) " rep
    
    if [[ $rep == [oO] ]] 
    then
        echo 0
    else
        echo 1
    fi


done
