#!/bin/bash

read var

if [[ $var == [Oo] ]]; then
    echo "Oui"
elif [[ $var == [Nn] ]];then
    echo "Non"
else
    echo "Erreur"
fi

