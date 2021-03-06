#!/bin/csh
if ( $#argv != 2 ) then
echo "Wrong args. Please input 2 positive integers."
exit
endif
if ( $argv[1] < 0 | $argv[2] < 0 ) then
echo "Integers must be positive. Please input 2 positive integers."
exit
endif

set greater = $argv[1]
set lesser = $argv[2]
if ( $argv[1] < $argv[2] ) then
set greater = $argv[2]
set lesser = $argv[1]
endif

set num = $greater
set den = $lesser

#GCD
set remain = `expr $num % $den`

while ( $remain != 0 )
set num = $den
set den = $remain
set remain = `expr $num \% $den`
end

set gcd = $den

#LCM
set lcm = `expr $greater \* $lesser`
set lcm = `expr $lcm \/ $gcd`

echo "The GCD of $greater and $lesser is $gcd"
echo "The LCM of $greater and $lesser is $lcm"
