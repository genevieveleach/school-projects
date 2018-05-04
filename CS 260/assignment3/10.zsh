#!/bin/zsh
getnum() {
    echo "Enter first number: "
    read n1
    echo "Enter second number: "
    read n2
    }
changenum() {
    op=0
    while [ $op -ne 1 ]
    do
	echo -e "\nWould you like to do another operation with the same integers (enter s or S) with different integers (enter d or D) or exit (enter e or E)?"
	read in
	case $in in
	    [sS])
	       	op=1
		;;
	    [dD])
		getnum
		op=1
		;;
	    [Ee])
		exit
		;;
	    *)
		echo "Invalid choice. Please enter again."
		;;
	esac
    done
    }
if [ $# -ne 2 ]
then
    echo "Wrong amount of args. Please provide 2 numbers to calculate."
    exit
fi
n1=$1
n2=$2
in="."
until [ $in = "e" ]|[ $in = "E" ]
do
    echo -e "\nMenu:"
    echo "Add the numbers (enter a or A)"
    echo "Subtract the numbers (enter s or S)"
    echo "Multiply the numbers (enter m or M)"
    echo "Divide the numbers (enter d or D)"
    echo "Exit (select e or E)"
    read in
    case $in in
	[Aa])
	    echo "$n1 + $n2 =" `expr $n1 + $n2`
	    ;;
	[Ss])
	    echo "$n1 - $n2 =" `expr $n1 - $n2`
	    ;;
	[Mm])
	    echo "$n1 * $n2 =" `expr $n1 \* $n2`
	    ;;
	[Dd])
	    echo "$n1 / $n2 =" `expr $n1 / $n2`
	    ;;
	[Ee])
	    exit
	    ;;
	*)
	    echo "Not a valid choice. Please select again."
	    continue
	    ;;
    esac

    echo -e "\nPlease choose another operation or exit."
    changenum
done
