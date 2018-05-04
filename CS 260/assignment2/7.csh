#!/bin/csh
set numArgs = $#argv
while ( $numArgs > 0 )
@ count = 1
while ( $count < $numArgs )
printf "$argv[$count] "
@ count++
end
echo "$argv[$numArgs]"
@ numArgs--
end
