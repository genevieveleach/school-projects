#!/bin/zsh
while (true)
do
      echo "Are you okay?"
read in
case $in in
    [Yy])
	echo "Glad to hear it."
	exit
	;;
    [Nn])
	echo "sorry that you are not feeling good"
	exit
	;;
    *)
	echo "Incorrect choice."
esac
done
