#!/bin/sh
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
while [ $in -ge 1 ]
do
    sum=`expr $sum + $in`
    let in--
done
echo "Sum of $n is $sum"
in=$n

#factorial
fact=1
n=$in
while [ $in -ge 1 ]
do
    fact=`expr $fact \* $in`
    let in--
done
echo "Factorial of $n is $fact"
