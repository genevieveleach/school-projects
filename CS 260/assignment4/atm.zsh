#!/bin/zsh
function transferCheckingtoSavings()
{
    echo "How much would you like to transfer from your checking account to savings account?"
    read amount

    if (( $(echo "$checking - $amount < 0" | bc -l) ))
    then
	echo "Transaction not completed."
	echo "The balance of your checking account is $"$checking
    else
	savings=`echo $savings + $amount | bc -l`
	checking=`echo $checking - $amount | bc -l`
	echo "New balance of your checking account is $"$checking
	echo "New balance of your savings account is $"$savings
    fi
}

function transferSavingstoChecking()
{
    echo "How much would you like to transfer from your savings account to checking account?"
    read amount

    if (( $(echo "$savings - $amount < 0" | bc -l) ))
    then
        echo "Transaction not completed."
        echo "The balance of your savings account is $"$savings
    else
        savings=`echo $savings - $amount | bc -l`
        checking=`echo $checking + amount | bc -l`
        echo "New balance of your savings account is $"$savings
		echo "New balance of your checking account is $"$checking

    fi
}

function withdrawCash()
{
    while true
    do
	echo "Would you like to withdraw from your checking account or your savings account?"
	echo "Please enter 1 for checking, or 2 for savings."
	read in
	case $in in
	    1)
		echo "Enter amount to withdraw."
		read amount
		if (( $(echo "$checking - $amount < 0" | bc -l) ))
		then
		    echo "Amount could not be withdrawn."
		    echo "The balance of your checking account is $"$checking
		else
		    checking=`echo $checking - $amount | bc -l`
		    echo "$amount is withdrawn. Please collect your cash."
		    echo "Remaining balance of your checking account is $"$checking
		fi
		break
		;;
	    2)
		echo "Enter amount to withdraw."
		read amount
		if (( $(echo "$savings - $amount < 0" | bc -l) ))
		then
		    echo "Amount could not be withdrawn."
		    echo "The balance of your savings account is $"$savings
		else
		    savings=`echo $savings - $amount | bc -l`
		    echo "$amount is withdrawn. Please collect your cash."
		    echo "Remaining balance of your savings account is $"$savings
		fi
		break
		;;
	    *)
		echo "Incorrect choice."
		continue
		;;
	esac
    done
}

function menu()
{
    while true
    do
    clear
    echo "*** Welcome to Cal Poly's ATM System ***"
    echo
    echo "(1) Transfer from checking account to savings account"
    echo "(2) Transfer from savings account to checking account"
    echo "(3) Savings account balance"
    echo "(4) Checking account balance"
    echo "(5) Withdraw Cash from either account"
    echo "(6) Exit"
    echo "==> Please select option (1-6)"

    read in

    case $in in
	1)
	    transferCheckingtoSavings
	    ;;
	2)
	    transferSavingstoChecking
	    ;;
	3)
	    echo "Balance in your savings account is $"$savings
	    ;;
	4)
	    echo "Balance in your checking account is $"$checking
	    ;;
	5)
	    withdrawCash
	    ;;
	6)
	    echo "Thank you for using the ATM system."
	    exit
	    ;;
	*)
	    ;;
    esac
    `sleep 2`
    clear
    done
}

savings=1000.00
checking=1000.00
tries=0
while true
do
    if [ $tries = 3 ]
    then
	echo "Too many illegal PINs. Try again later."
	exit
    fi

    echo "*** Welcome to Cal Poly's ATM System ***"
    echo
    echo "Please enter your pin: "
    read in

    if [ $in = 111 ]
    then
	menu
	break
    else
	tries=`expr $tries + 1`
    fi
    clear
done
