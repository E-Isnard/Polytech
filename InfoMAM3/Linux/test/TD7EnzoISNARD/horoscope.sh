#!/bin/bash

if [ $# -eq 2 ]
then
mois=$(ls ./datFiles |  grep "^$1")
if [[ ! -z $mois ]]
then
line=$(cat ./datFiles/$mois | grep "^$2")
theme=$(echo $line | cut -d ":" -f2)
prevision=$(echo $line | cut -d ":" -f3)
echo $theme":"
echo $prevision
fi
fi
