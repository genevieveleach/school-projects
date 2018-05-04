#!/bin/sh
count=0
while [ "$count" -ne 10 ]
do
    echo $count
    count=`expr $count + 1`
done
