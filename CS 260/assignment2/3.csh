#!/bin/csh
switch ( $1 )
case 100:
echo "You got an A"
breaksw
case 9[0-9]:
echo "You got an A"
breaksw
case 8[0-9]:
echo "You got a B"
breaksw
case 7[0-9]:
echo "You got a C"
breaksw
case 6[0-9]:
echo "You got a D"
breaksw
case [1-5][0-9]:
echo "You failed."
breaksw
case [0-9]:
echo "You failed."
breaksw
default:
echo "Invalid score."
endsw
