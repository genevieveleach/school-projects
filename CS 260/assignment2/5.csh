#!/bin/csh
echo "Please enter a color:"
set in = $<
switch ( $in )
case [Bb]l?*:
echo "The sky color is $in"
breaksw
case red:
echo "The sun is sometimes this color"
breaksw
case yellow:
echo "The sun is sometimes this color"
breaksw
default:
echo "The color is not in any of the categories defined."
endsw
