#!/bin/zsh
echo $*
while [ $# -gt 1 ]
do
    shift
    echo $*
done
