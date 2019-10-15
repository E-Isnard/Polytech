#!/bin/bash

words=$(cat $1 | tr [:punct:] "\n" | tr -d [:blank:] | grep -v "^$" | sort | uniq)
liste=$(cat $1 | tr [:punct:] "\n" | tr -d [:blank:] | grep -v "^$" | sort)

echo "Nombre de mots dans ce texte:" $(echo $liste | wc -w)
echo "Nombre de mots différents dans ce texte:" $(echo $words | wc -w)
echo "Occurence des mots: "

for word in $words;do echo "$liste"| echo $(grep -c $word) "$word";done
