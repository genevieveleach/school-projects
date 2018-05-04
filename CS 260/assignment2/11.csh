#!/bin/csh
printf "Please enter an integer between 1 and 10: "
set num = $<
while ( $num < 1 || $num > 10 )
prinf "Incorrect range of numbers. Please enter a number 1-10."
set num = $<
end

#sum
set sum = 0
set n = $num
printf "Sum of $n is "
while ( $num >= 1 )
set sum = `expr $sum + $num`
@ num = $num - 1
end
printf "$sum("
set num = $n
while( $n != 1 )
printf "$n+"
@ n = $n - 1
end
printf "$n)\n"

#factorial
set fact = 1
set n = $num
printf "Factorial of $n is "
while ( $num >= 1 )
set fact = `expr $fact \* $num`
@ num = $num - 1
end
printf "$fact("
while ( $n != 1 )
printf "$n*"
@ n = $n - 1
end
printf "$n)\n"
