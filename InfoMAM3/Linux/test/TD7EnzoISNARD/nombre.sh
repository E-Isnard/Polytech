#!/bin/bash

if [ $# -eq 2 ]
then
if [ $1 -gt $2 ]
then
echo "$1 est supérieur à $2"
elif [ $2 -gt $1 ]
then
echo "$2 est supérieur à $1"
else
echo "$1 est égal à $2"
fi
fi


