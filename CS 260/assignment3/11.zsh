#!/bin/zsh
echo "Enter an integer 1-10. "
read in
while [ $in -lt 1 -o $in -gt 10 ]
do
    echo "Incorrect range of numbers. Please enter a number 1-10."
    read in
done
echo

#sum
sum=0
n=$in
echo -n "Sum of $in is "
while [ $in -ge 1 ]
do
    sum=`expr $sum + $in`
    let in--
done
in=$n
echo -n "$sum("
while [ $n -ne 1 ]
do
    echo -n "$n+"
    n=`expr $n - 1`
done
echo "1)"

#factorial
fact=1
n=$in
echo -n "Factorial of $n is "
while [ $in -ge 1 ]
do
    fact=`expr $fact \* $in`
    let in--
done
echo -n "$fact("
while [ $n -ne 1 ]
do
    echo -n "$n*"
    n=`expr $n - 1`
done
echo "1)"
