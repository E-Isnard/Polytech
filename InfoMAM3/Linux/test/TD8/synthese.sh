#!/bin/bash

function ajouter_extension_wav() {
    echo $1.wav
}

function enlever_ponctuation() {
    echo $1 | tr -d [:punct:]
}

function mettre_en_minuscule() {
    read var

    echo $var | tr -s A-Z a-z
}

function fichier_existe() {
    if [ -f $1 ]; then
        return 0
    else
        return 1
    fi
}

#On a déjà $* pour concatener_noms.

function preparer_sons() {
    out=""
    for word in $1; do
        fich=$(ajouter_extension_wav $word)
        if [ -f sons/$fich ]; then
            out=$out" "$fich
        else
            echo "Mot non reconnu: $word"
        fi

    done
    echo $out
}

function prononcer_ligne() {

    ligne=$(echo $1 | tr -d [:punct:] | tr -s A-Z a-z)

    out=$(preparer_sons "$ligne" | grep -v "^Mot non reconnu")

    echo $out
    #play -q $out

}

function prononcer_chaque_ligne() {
    while read line; do
        prononcer_ligne "$line"
    done

}

function lister_dico() {

    for fich in $(ls sons); do
        echo $(echo $fich | cut -d "." -f1)
    done

}

function affiche_usage() {
    echo "Usage:
    $0 -h|--help : affiche cette aide
    $0 -d|--dico : affiche le dictionnaire
    $0 -p|--pro ligne : prononce la ligne
    $0 -i|--inter : mode interactif"

}

if [ $# -eq 1 ]; then
    if [[ $1 = "-h" || $1 = "--help" ]]; then
        affiche_usage

    elif
        [[ $1 = "-d" || $1 = "--dico" ]]
    then
        lister_dico

    elif
        [[ $1 = "-i" || $1 = "--inter" ]]
    then
        prononcer_chaque_ligne
    else
        exit 1
    fi

elif [ $# -eq 2 ]; then
    if [[ $1 = "-p" || $1 = "--pro" ]]; then
        prononcer_ligne $2
    else
        exit 1
    fi

else
    exit 1
fi

