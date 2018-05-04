#!/bin/zsh
echo "Enter a color:"
read in
case $in in
    [Bb]l?*)
	echo "the sky color is $in"
	;;
    red|yellow)
	echo "the sun is sometimes this color."
	;;
    *)
	echo "the color is not in any of the categories defined"
	;;
esac
    
