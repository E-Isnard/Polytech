#!/bin/bash
if [ $# -eq 1 ] 
then
    userLine=$(cat /etc/shadow | grep $1)
    if [ -z $userLine ]
    then
        echo "L'utilisateur n'existe pas"
    else
        echo "L'utilisateur existe"
    fi
fi
