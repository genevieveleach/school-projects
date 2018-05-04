#!/bin/bash

phonebook="datebook"

# Check if file exists
if [ ! -f $phonebook ]
then
    echo "Phonebook file not found. Exiting."
    exit
fi

### Functions ###
function list()
{
    case $1 in
	1)
	    # alphabetical first name
	    echo "List of records in alphabetical order of first name: "
	    echo
	    # sort and copy to temp file
	    sort -k1d -t" " "$phonebook" > "temp"
	    # copy to phonebook file
	    cp "temp" "$phonebook"
	    # remove temp file
	    rm "temp"
	    #show sorted file
	    cat "$phonebook"
	    echo
	    ;;
	2)
	    # alphabetical last name
	    echo "List of records in alphabetical order of last name: "
	    echo
	    # same steps as option (1)
	    sort -k2d -t" " "$phonebook" > "temp"
	    cp "temp" "$phonebook"
	    rm "temp"
	    cat "$phonebook"
	    echo
	    ;;
	3)
	    # reverse alphabetical first name
	    echo "List of records in reverse alphabetical order of first name: "
	    echo
	    sort -k1dr -t" " "$phonebook" > "temp"
	    cp "temp" "$phonebook"
	    rm "temp"
	    cat "$phonebook"
	    echo
	    ;;
	4)
	    # reverse alphabetical last name
	    echo "List of records in reverse alphabetical order of last name: "
	    echo
	    sort -k2dr -t" " "$phonebook" > "temp"
	    cp "temp" "$phonebook"
	    rm "temp"
	    cat "$phonebook"
	    echo
	    ;;
	*)
	    echo "How did you even get here?"
	    exit
	    ;;
    esac
}

function search()
{
    case $1 in
	1)
	    # by last name
	    echo -n "Enter the last name you want to search: "
	    read lastName
	    echo "Records with last name $lastName: "
	    echo
	    awk -v var="$lastName" -F'[ :]' '{if ($2 ~ var) {print;} }' "$phonebook"
	    echo
	    ;;
	2)
	    # by birthday in given year
	    echo -n "Enter the year you want to search: "
	    read year
	    echo "Records with year $year: "
	    echo
	    awk -v var="$year" -F'[/:]' '{if ($7 == var) {print;} }' "$phonebook"
	    echo
	    ;;
	3)
	    # by birthday in given month
	    echo -n "Enter the month you want to search: "
	    read month
	    echo "Records with month $month: "
	    echo
	    awk -v var="$month" -F'[/:]' '{if ($5 == var) {print;} }' "$phonebook"
	    ;;
	*)
	    echo "How did you even get here?"
	    exit
	    ;;
    esac
}

function insertRecord()
{
    number=""
    while true
    do
	# get mobile phone number w/ validation
	while true
	do
	    echo -n "Please enter mobile phone number (no country code, no spaces): "
	    read number
    	    if [[ $number =~ ^[0-9]{10}$ ]]
	    then
		break
	    else
		echo "Invalid format. Phone number must be 10 numbers, 0-9."
	    fi
	done
	# format number
	number=${number:0:3}"-"${number:3:3}"-"${number:6:4}
	# check if exists, default false
	exists=false
	# if exists, set to true
	exists=`awk -F: -v var="$number" '{if ($3 ~ var) {print "true";}}' "$phonebook"` 
	if [[ $exists =~ "true" ]]
	then
	    echo "Primary key (mobile phone number) already exists. Enter a new phone number."
	    continue
	else
	    break
	fi
    done

    echo "Primary key (mobile phone number): $number"

    # get first name w/validation
    firstName=""
    while true
    do
	echo -n "Please enter the first name: "
	read firstName
	if [[ $firstName =~ ^[A-Z][a-z]+$ ]]
	then
	    break
	else
	    echo "Invalid format. First name should be a capital letter followed by alphabet characters."
	    continue
	fi
    done

    echo "First name: $firstName"

    # get last name w/validation
    lastName=""
    while true
    do
	echo -n "Please enter the last name: "
        read lastName
        if [[ $lastName =~ ^[A-Z][a-z]+$ ]]
        then
            break
        else
            echo "Invalid format. Last name should be a capital letter followed by alphabet characters."
            continue
	fi
    done

    echo "Last name: $lastName"

    # get home phone number w/ validation
    homeNumber=""
    while true
    do
	echo -n "Please enter home phone number (no country code, no spaces): "
	read homeNumber
    	if [[ $homeNumber =~ ^[0-9]{10}$ ]]
	then
	    break
	else
	    echo "Invalid format. Phone number must be 10 numbers, 0-9."
	fi
    done
    # format number
    homeNumber=${homeNumber:0:3}"-"${homeNumber:3:3}"-"${homeNumber:6:4}

    echo "Home phone number: $homeNumber"

    # get address w/o validation
    address=""
    echo -n "Please enter your address (Street address, city, state). There is no validation, please type correct format: "
    read address

    # zip code w/ validation
    zip=""
    while true
    do
	echo -n "Please enter zip code: "
	read zip
	if [[ $zip =~ ^[0-9]{5}$ ]]
	then
	    break
	else
	    echo "Invalid format. Zipcode is 5 digits long, 0-9."
	    continue
	fi
    done
    address=$address" "$zip

    echo "Full address: $address"
    
    # get birth date w/ validation
    birthday=""
    while true
    do
	echo -n "Please enter birthday (MM/DD/YY): "
	read birthday
	if [[ $birthday =~ ^[0-9]{1,2}/[0-9]{2}/[0-9]{2}$ ]]
	then
	    break
	else
	    echo "Invalid format. Birthday must be in format MM/DD/YY."
	fi
    done

    echo "Birthday: $birthday"
    
    # get salary w/ validation
    salary=""
    while true
    do
	echo -n "Please enter salary: "
	read salary
	if [[ $salary =~ ^[0-9]+$ ]]
	then
	    break
	else
	    echo "Invalid format. Salary must be numerical with at least one digit 0-9."
	fi
    done

    echo "Salary: $salary"

    newRecord=$firstName" "$lastName":"$homeNumber":"$number":"$address":"$birthday":"$salary

    echo "Record to insert: $newRecord"
    echo
    #put record into phonebook
    echo "$newRecord" >> "$phonebook"

    # sort
    # sort by last name, put into temp file
    sort -k2d -t" " "$phonebook" > "temp"
    # copy into phonebook file
    cp "temp" "$phonebook"
    # remove temp file
    rm "temp"
    # show sorted file
    cat "$phonebook"
    echo
}

function deleteRecord()
{
    case $1 in
	1)
	    # by phone number
	    while true
	    do
		echo -n "Enter the mobile phone number you want to delete (10 digits, no formatting): "
		read number
		if [[ $number =~ ^[0-9]{10}$ ]]
		then
		    break
		else
		    echo "Invalid format. Phone number should be 10 digits, 0-9."
		    continue;
		fi
	    done
	    #format number
	    number=${number:0:3}"-"${number:3:3}"-"${number:6:4}
	    echo "Records with mobile phone number: $number"
	    echo
	    awk -v var="$number" -F'[:]' '{if ($3 ~ var) {print;} }' "$phonebook"
	    echo
	    echo "Now deleting records..."
	    echo "New database file: "
	    echo
	    # sort/copy to temp file
	    awk -v var="$number" -F'[:]' '{if ($3 !~ var) {print;} }' "$phonebook" > "temp"
	    # copy from temp file to database file
	    cp "temp" "$phonebook"
	    # remove temp file
	    rm "temp"
	    # print sorted file
	    cat "$phonebook"
	    echo
	    ;;
	2)
	    # by last name
	    echo -n "Enter the last name you want to delete: "
	    read lastName
	    echo "Records with last name: $lastName"
	    echo
	    awk -v var="$lastName" -F'[ :]' '{if ($2 ~ var) {print;} }' "$phonebook"
	    echo
	    echo "Now deleting records..."
	    echo "New database file:"
	    echo
	    # sort/copy to temp file
	    awk -v var="$lastName" -F'[ :]' '{if ($2 !~ var) {print;} }' "$phonebook" > "temp"
	    # copy from temp file to database file
	    cp "temp" "$phonebook"
	    # remove temp file
	    rm "temp"
	    # print sorted file
	    cat "$phonebook"
	    echo
	    ;;
	*)
	    echo "How did you even get here?"
	    exit
	    ;;
    esac
}


function menu()
{
    echo "1) List of records in alphabetical order of first name"
    echo "2) List of records in alphabetical order of last name"
    echo "3) List of records in reverse alphabetical order of first name"
    echo "4) List of records in reverse alphabetical order of last name"
    echo "5) Search for a record by last name"
    echo "6) Search for a record by birthday in a given year"
    echo "7) Search for a record by birthday in a given month"
    echo "8) Insert a record"
    echo "9) Delete record by phone number"
    echo "10) Delete record by last name"
    echo "0) Exit"
    echo -n "Please enter an option: "
    read in

    case $in in
	1)
	    list 1
	    menu
	    ;;
	2)
	    list 2
	    menu
	    ;;
	3)
	    list 3
	    menu
	    ;;
	4)
	    list 4
	    menu
	    ;;
	5)
	    search 1
	    menu
	    ;;
	6)
	    search 2
	    menu
	    ;;
	7)
	    search 3
	    menu
	    ;;
	8)
	    insertRecord
	    menu
	    ;;
	9)
	    deleteRecord 1
	    menu
	    ;;
	10)
	    deleteRecord 2
	    menu
	    ;;
	0)
	    echo "Exiting."
	    exit
	    ;;
	*)
	    echo "Invalid option."
	    menu
	    ;;
    esac
}

### Main ###
menu
