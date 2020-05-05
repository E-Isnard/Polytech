#!/bin/bash

function affiUsage() {
    echo "USAGE $0 [-p] fichiers"
}

function trouveAppli() {
    ext=$(echo $1 | rev | cut -d "." -f1 | rev)

    if [[ $ext = "c" || $ext = "h" || $ext = "txt" || $ext = "sh" ]]; then
        echo nano
    elif [[ $ext = "html" ]]; then
        echo firefox
    elif [[ $ext = "doc" ]]; then
        echo libreoffice
    fi
}

flag=0

if [ $# -eq 0 ]; then
    affiUsage >&2
elif [[ $1 = "-p" ]]; then
    flag=1
fi

args=("$@")
args=${args[@]:flag}

if [ $flag -eq 1 ]; then
    for arg in $args; do
        app=$(trouveAppli $arg)
        read -p "Pressez une touche ..."
        $app $arg 

    done
else
    for arg in $args; do
        app=$(trouveAppli $arg)
        $app $arg 

    done

fi
