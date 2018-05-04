#!/bin/csh
set tries=0
while ( 1 )
    if ( $tries == 3 ) then
	echo "Too many illegal PINs. Try again later."
	exit
    endif

    echo "***Welcome to Cal Poly's ATM ***"
    echo "Please enter your pin:"
    set in=$<

    if ( $in == 111 ) then
	break
    else
	clear
	@ tries++
    endif
end

set checking=1000.00
set savings=1000.00

while ( 1 )
    clear
    echo "*** Welcome to Cal Poly's ATM System ***"
    echo
    echo "(1) Transfer from checking account to savings account"
    echo "(2) Transfer from savings account to checking account"
    echo "(3) Savings account balance"
    echo "(4) Checking account balance"
    echo "(5) Withdraw Cash from either account"
    echo "(6) Exit"
    echo "==> Please select option (1-6):"
    set choice=$<

    switch ( $choice )
    case 1:
	echo "Please enter the amount to transfer."
	set amount=$<
	if ( `echo "$checking - $amount < 0" | bc -l` )	then
	    echo "Transaction not completed."
	    echo 'The balance of your checking account is $'$checking
	else
	    set savings=`echo "$savings + $amount" | bc -l`
	    set checking=`echo "$checking - $amount" | bc -l`
	    echo 'New balance of your checking account is $'$checking
	    echo 'New balance of your savings account is $'$savings
	endif
	breaksw
    case 2:
	echo "Please enter the amount to transfer."
        set amount=$<
        if ( `echo "$savings - $amount < 0" | bc -l` ) then
            echo "Transaction not completed."
            echo 'The balance of your savings account is $'$savings
        else
            set savings=`echo "$savings - $amount" | bc -l`
            set checking=`echo "$checking + $amount" | bc -l`
            echo 'New balance of your checking account is $'$checking
            echo 'New balance of your savings account is $'$savings
        endif
        breaksw
    case 3:
	echo 'Balance of your savings account is $'$savings
	breaksw
    case 4:
	echo 'Balance of your checking account is $'$checking
	breaksw
    case 5:
	while ( 1 )
	    echo "Would you like to withdraw from your checking account or your savings account?"
	    echo "Please enter 1 for checking, or 2 for savings."
	    set in=$<
	    switch ( $in )
            case 1:
                echo "Enter amount to withdraw."
                set amount=$<
                if ( `echo "$checking - $amount < 0" | bc -l` ) then
                    echo "Amount could not be withdrawn."
                    echo 'The balance of your checking account is $'$checking
                else
                    set checking=`echo "$checking - $amount" | bc -l`
                    echo "$amount is withdrawn. Please collect your cash."
                    echo 'Remaining balance of your checking account is $'$checking
                endif
				break
                breaksw
            case 2:
                echo "Enter amount to withdraw."
                set amount = $<
                if ( `echo "$savings - $amount < 0" | bc -l` ) then
                    echo "Amount could not be withdrawn."
                    echo "The balance of your savings account is $"$savings
                else
                    set savings=`echo "$savings - $amount" | bc -l`
                    echo "$amount is withdrawn. Please collect your cash."
                    echo 'Remaining balance of your savings account is $'$savings
                endif
                break
			breaksw
            default:
                echo "Unknown choice."
                continue
                breaksw
		endsw
	end
	breaksw
    case 6:
		echo "Thank you for using the ATM system."
		exit
		breaksw
    default:
		echo "Unknown choice."
		breaksw
	endsw
    `sleep 3`
    clear
end
