#!/bin/csh
if ( $#argv != 2 ) then
echo "Wrong amount of args. Please provide 2 numbers to calculate."
exit
endif
set in = ""
set n1 = $argv[1]
set n2 = $argv[2]
while ( $in != e | $in != E )
echo "Menu:"
echo "Add the numbers (enter a or A)"
echo "Subtract the numbers (enter s or S)"
echo "Multiply the numbers (enter m or M)"
echo "Divide the numbers (enter d or D)"
printf "Exit (select e or E)\n\n"
set in = $<
switch ( $in )
case [Aa]:
echo "$n1 + $n2 =" `expr $n1 + $n2`
breaksw
case [Ss]:
echo "$n1 - $n2 =" `expr $n1 - $n2`
breaksw
case [Mm]:
echo "$n1 * $n2 =" `expr $n1 \* $n2`
breaksw
case [Dd]:
echo "$n1 / $n2 =" `expr $n1 / $n2`
breaksw
case [eE]
exit
breaksw
default:
echo "Not a valid choice. Please select again."
echo
continue
endsw

set v = 0
while ( $v != 1 )
echo ""
echo "Would you like to do another operation with the same integers, different integers, or exit?"
echo "Please enter S or s for same, d or D for different, e or E for exit."
set nextchoice = $<
switch ( $nextchoice )
case [Ss]:
set v = 1
echo ""
breaksw
case [dD]:
printf "\nPlease enter integer 1: "
set n1 = $<
printf "Please enter integer 2: "
set n2 = $<
set v = 1
echo ""
breaksw
case [eE]:
exit
breaksw
default:
echo "Not a valid choice. Please select again."
continue
endsw
end
end
