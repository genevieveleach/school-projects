#!/bin/sh
echo Are you OK?
while read in; do
    if [[ "$in" == "y" ]] || [[ "$in" == "Y" ]];
    then
	echo Glad to hear it.
	break
    elif [[ "$in" == "n" ]] || [[ "$in" == "N" ]];
    then
	echo Sorry that you are not feeling good.
	break
    else
	echo Incorrect choice.
	echo Are you OK?
    fi
done    
