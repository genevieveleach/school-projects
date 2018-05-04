#!/bin/sh

echo Enter a number: 
read in
mod=$(( in % 2 ))
if [ $mod -eq 0 ]
then
    echo $in is even
else
    echo $in is odd
fi
