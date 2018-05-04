#!/bin/zsh
echo "Please enter a number."
read in
if [ `expr $in % 2` -ne 0 ]
then
    echo "$in is odd"
else
    echo "$in is even"
fi

