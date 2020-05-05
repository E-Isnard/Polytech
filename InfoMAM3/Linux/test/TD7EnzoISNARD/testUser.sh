#!/bin/bash

function userExist () {

userLine=$(grep "^$1" /etc/passwd)

if [[ -z $userLine ]]
then
echo 0
else
echo 1
fi
}

function getUID () {

echo $(grep "^$1" /etc/passwd | cut -d ":" -f3)

}
read user
if [ $# -eq 0 ] 
then
var=$(userExist $user)
if [ $var -eq 0 ] 
then
echo "L'utilisateur n'existe pas "
else
echo $(getUID $user)
fi
fi
