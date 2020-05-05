#!/bin/sh

chemin=$(pwd)
nom=$(echo $0 | tail -c +2)
echo $chemin$nom
echo $0
