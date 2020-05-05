#!/bin/bash

read note

if (( note >15)) && ((note < 21));
then
echo "très bien" 
elif ((note <16)) && (( note >13))
then
echo "bien"
elif ((note <14)) && (( note > 11))
then
echo "assez bien"
elif ((note < 13)) && ((note > 9))
then
echo "moyen"
else
echo "insuffisant"
fi
