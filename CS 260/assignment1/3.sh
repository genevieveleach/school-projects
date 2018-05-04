#!/bin/sh
# takes in score from command line
case $1 in
    100)
	echo "you got A"
	;;
    9[0-9])
	echo "you got A"
	;;
    8[0-9])
	echo "you got B"
	;;
    7[0-9])
	echo "you got C"
	;;
    6[0-9])
	echo "you got D"
	;;
    [0-5]|[1-5][0-9])
	echo "you failed."
	;;
    *)
	echo "invalid score. are you sure you took the test?"
esac
