#!/bin/csh
echo "Please enter a number: "
set in = $<
if ( $in % 2 == 0 ) then
echo "$in is even"
else
echo "$in is odd"
endif
