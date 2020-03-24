#!/bin/bash

if [[ $# -eq 1 ]] 
then
    
    if [ -e $1 ] 
    then
        permission=""

        if [ -r $1 ]
        then
        permission=$permission"r"
        fi

        if [ -w $1 ]
        then
         permission=$permission"w"
        fi

        if [ -x $1 ]   
        then
        permission=$permission"x"
        fi
        
        if [ -f $1 ]
        then
            echo "Le fichier est un fichier($permission)"
        fi

        if [ -d $1 ]
        then
            echo "Le fichier est un dossier ($permission)"
        fi

    
    else
        echo "Le fichier n'existe pas"
    
    fi
    
fi