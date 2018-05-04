#!/bin/zsh
if [ $# -ne 1 ]
then
    echo "Please enter a single score between 0 and 100 in the command line when running the script."
    exit
fi

if [ $1 -lt 0 -o $1 -gt 100 ]
then
    echo "Invalid number; please enter a score between 0 and 100 in the command line when running the script."
    exit
fi

case $1 in
    100|9[0-9])
	echo "You got A."
	;;
    8[0-9])
	echo "You got B."
	;;
    7[0-9])
	echo "You got C."
	;;
    6[0-9])
	echo "You got D"
	;;
    *)
	echo "You failed."
	;;
esac
