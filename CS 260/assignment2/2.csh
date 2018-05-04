#!/bin/csh
echo "Are you okay?"
set in = $<
while ( 1 )
switch ( $in )
case [Yy]:
echo "glad to hear it"
exit
breaksw
case [Nn]:
echo "sorry that you are not feeling good"
exit
breaksw
default:
echo "Incorrect choice."
echo "Are you okay?"
set in = $<
endsw
end
