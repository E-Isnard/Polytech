array=( "$@" )

n=$#

for (( i=0; i<n-1; i++ )); do

   for (( j=0; j<n-i-1; j++ )); do
      if (( array[j] > array[j+1] )); then
         tmp=${array[j]}
         array[j]=${array[j+1]}
         array[j+1]=$tmp
      fi
   done

done

echo ${array[@]}
