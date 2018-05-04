#!/usr/bin/perl
use warnings;
use strict;

my $phonebook="datebook";

# check if file exists
if ( ! -f "$phonebook" ) {
    die "Database file not found. Exiting...";
}

### Functions ###

sub list {
    if ( $_[0] == 1 ) {
	# alphabetical first name
	print "List of records in alphabetical order of first name:\n\n";
	# sort and copy to temp file
	system("sort -k1d -t\" \" \"$phonebook\" > \"temp\"");
	# copy to phonebook file
	system("cp \"temp\" \"$phonebook\"");
	# remove temp file
	system("rm \"temp\"");
	# show sorted file
	system("cat \"$phonebook\"");
	print "\n";
    } elsif ( $_[0] == 2 ) {
	# alphabetical last name
	print "List of records in alphabetical order of last name:\n\n";
	# same steps as option (1)
	system("sort -k2d -t\" \" \"$phonebook\" > \"temp\"");
	system("cp \"temp\" \"$phonebook\"");
	system("rm \"temp\"");
	system("cat \"$phonebook\"");
	print "\n";
    } elsif ( $_[0] == 3 ) {
	# reverse alphabetical first name
	print "List of records in reverse alphabetical order of first name: \n\n";
	system("sort -k1dr -t\" \" \"$phonebook\" > \"temp\"");
	system("cp \"temp\" \"$phonebook\"");
	system("rm \"temp\"");
	system("cat \"$phonebook\"");
	print "\n";
    } elsif ( $_[0] == 4 ) {
	# reverse alphabetical last name
	print "List of records in reverse alphabetical order of last name: \n\n";
	system("sort -k2dr -t\" \" \"$phonebook\" > \"temp\"");
	system("cp \"temp\" \"$phonebook\"");
	system("rm \"temp\"");
	system("cat \"$phonebook\"");
	print "\n";
    } else {
	die "How did you get here?";
    }
}

sub search {
    if ( $_[0] == 1) {
	# by last name
	print "Enter the last name you want to search: ";
	my $lastName = <STDIN>;
	chomp($lastName);
	print "Records with last name: ".$lastName."\n\n";
	system("awk -v var=\"$lastName\" -F'[ :]' '{if (\$2 == var) {print;} }' \"$phonebook\"");
	print "\n"
    } elsif ( $_[0] == 2 ) {
	# by birthday in given year
	print "Enter the year you want to search: ";
	my $year = <STDIN>;
	chomp($year);
	print "Records with year: ".$year."\n\n";
	system("awk -v var=\"$year\" -F'[/:]' '{if (\$7 == var) {print;} }' \"$phonebook\"");
	print "\n";
    } elsif ( $_[0] == 3 ) {
	# by birthday in given month
	print "Enter the month you want to search: ";
	my $month = <STDIN>;
	chomp($month);
	print "Records with month: ".$month."\n\n";
	system("awk -v var=\"$month\" -F'[:/]' '{if (\$5 == var) {print;} }' \"$phonebook\"");
	print "\n";
    } else {
	die "How did you get here?";
    }
}

sub insertRecord {
    # get mobile phone number w/ validation
    my $number = "";
    while ( 1 ) {
	while (1) {
	    print "Please enter mobile phone number (no country code, no format): ";
	    $number = <STDIN>;
	    chomp($number);
	    if ( $number =~ /^[0-9]{10}$/ ) {
		last;
	    } else {
		print "Invalid format. Phone number must be 10 numbers, 0-9.\n"
	    }
	}
	# format number
	$number = substr($number, 0, 3)."-".substr($number, 3, 3)."-".substr($number, 6, 4);
	# if exists, set to true
	my $exists = system("awk -F: -v var=\"$number\" '{if (\$3 !~ var) {print \"true\";}}' \"$phonebook\"");
	if ( $exists =~ /true/ ) {
	    print "Primary key (mobile phone number) already exists. Enter a new phone number.\n";
	    $exists = "false";
	} else {
	    last;
	}
    }
    print "Primary key (mobile phone number): ".$number."\n";
    
    # get first name w/ validation
    my $firstName = "";
    while ( 1 ) {
	print "Please enter the first name: ";
	$firstName = <STDIN>;
	chomp($firstName);
	if ( $firstName =~ /^[A-Z][a-z]+$/ ) {
	    last;
	} else {
	    print "Invalid format. First name should be a capital letter followed by alphabet characters.\n";
	}
    }
    print "First name: ".$firstName."\n";

    # get last name w/ validation
    my $lastName = "";
    while ( 1 ) {
	print "Please enter the last name: ";
	$lastName = <STDIN>;
	chomp($lastName);
	if ( $firstName =~ /^[A-Z][a-z]+$/ ) {
	    last;
	} else {
	    print "Invalid format. Last name should be a capital letter followed by alphabet character.\n";
	}
    }
    print "Last name: ".$lastName."\n";

    # get home phone number w/ validation
    my $homeNumber = "";
    while ( 1 ) {
	print "Please enter home phone number (no country code, no spaces): ";
	$homeNumber = <STDIN>;
	chomp($homeNumber);
        if ( $homeNumber =~ /^[0-9]{10}$/ ) {
	    last;
        } else {
	    print "Invalid format. Phone number must be 10 numbers, 0-9.\n"
        }
    }
    # format number
    $homeNumber = substr($homeNumber, 0, 3)."-".substr($homeNumber, 3, 3)."-".substr($homeNumber, 6, 4);
    print "Home phone number: ".$homeNumber."\n";

    # get address w/o validation
    my $address = "";
    print "Please enter your address (Street address, city, state). There is no validation, please type correct format:";
    $address = <STDIN>;
    chomp($address);

    # get zip code w/ validation
    my $zip = "";
    while ( 1 ) {
	print "Please enter zip code: ";
	$zip = <STDIN>;
	chomp($zip);
	if ( $zip =~ /^[0-9]{5}$/ ) {
	    last;
	} else {
	    print "Invalid format. Zip code is 5 digits long, 0-9.";
	}
    }
    $address=$address." ".$zip;

    print "Full address: ".$address."\n";

    # get birth date w/ validation
    my $birthday = "";
    while ( 1 ) {
	print "Please enter birthday (MM/DD/YY): ";
	$birthday = <STDIN>;
	chomp($birthday);
	if ( $birthday =~ /^[0-9]{1,2}\/[0-9]{2}\/[0-9]{2}$/ ) {
	    last;
	} else {
	    print "Invalid format. Birthday must be in format MM/DD/YY.\n";
	}
    }
    print "Birthday: ".$birthday."\n";

    # get salary w/ validation
    my $salary = "";
    while ( 1 ) {
	print "Please enter salary: ";
	$salary = <STDIN>;
	chomp($salary);
	if ( $salary =~ /^[0-9]+$/ ) {
	    last;
	} else {
	    print "Invalid format. Salary must be numberical with at least one digit 0-9.";
	}
    }

    print "Salary: ".$salary."\n";

    my $newRecord = $firstName." ".$lastName.":".$homeNumber.":".$number.":".$address.":".$birthday.":".$salary;

    print "Record to insert: ".$newRecord."\n\n";
    # put record into phonebook
    system("echo $newRecord >> $phonebook");
    # sort by last name, put into temp file
    system("sort -k2d -t\" \" \"$phonebook\" > \"temp\"");
    # copy into phonebook file
    system("cp \"temp\" \"$phonebook\"");
    # remove temp file
    system("rm \"temp\"");
    # show sorted file
    system("cat \"$phonebook\"");
    print "\n"; 
}

sub deleteRecord {
    if ($_[0] == 1 ) {
	# by phone number
	my $number = "";
	while ( 1 ) {
	    print "Enter the mobile phone number you want to delete (10 digits, no formatting): ";
	    $number = <STDIN>;
	    chomp($number);
	    if ( $number =~ /^[0-9]{10}$/ ) {
		last;
	    } else {
		print "Invalid format. Phone number should be 10 digits, 0-9.\n"
	    }
	}
	# format number
	$number = $number = substr($number, 0, 3)."-".substr($number, 3, 3)."-".substr($number, 6, 4);
	
	print "Records with mobile phone number: ".$number."\n\n";
	system("awk -v var=\"$number\" -F'[:]' '{if (\$3 ~ var) {print;} }' \"$phonebook\"");
	print "\nNow deleting records...\nNew database file:\n\n";

	# sort/copy to temp file
	system("awk -v var=\"$number\" -F'[:]' '{if (\$3 !~ var) {print;} }' \"$phonebook\" > \"temp\"");
	# copy from temp file to database file
	system("cp \"temp\" \"$phonebook\"");
	# remove temp file
	system("rm \"temp\"");
	# print sorted file
	system("cat \"$phonebook\"");
	print "\n"
    } elsif ($_[0] == 2 ) {
	# by last name
	print "Enter the last name you want to delete: ";
	my $lastName = <STDIN>;
	chomp($lastName);

	print "Records with last name: ".$lastName."\n\n";
	system("awk -v var=\"$lastName\" -F'[ :]' '{if (\$2 ~ var) {print;} }' \"$phonebook\"");
	print "\nNow deleting records...\nNew database file:\n\n";
	
	# sort/copy to temp file
	system("awk -v var=\"$lastName\" -F'[ :]' '{if (\$2 !~ var) {print;} }' \"$phonebook\" > \"temp\"");
        # copy from temp file to database file
        system("cp \"temp\" \"$phonebook\"");
        # remove temp file
        system("rm \"temp\"");
        # print sorted file
        system("cat \"$phonebook\"");
        print "\n"
    } else {
	die "How did you get here?";
    }
}

sub menu {
    print "1) List of records in alphabetical order of first name\n";
    print "2) List of records in alphabetical order of last name\n";
    print "3) List of records in reverse alphabetical order of first name\n";
    print "4) List of records in reverse alphabetical order of last name\n";
    print "5) Search for a record by last name\n";
    print "6) Search for a record by birthday in a given year\n";
    print "7) Search for a record by birthday in a given month\n";
    print "8) Insert a record\n";
    print "9) Delete record by phone number\n";
    print "10) Delete record by last name\n";
    print "0) Exit\n";
    print "Please enter an option: ";

    my $in = <STDIN>;
    chomp($in);

    if ( $in == 1 ) {
	list(1);
    } elsif ( $in == 2 ) {
	list(2);
    } elsif ( $in == 3 ) {
	list(3);
    } elsif ( $in == 4 ) {
	list(4);
    } elsif ( $in == 5 ) {
	search(1);
    } elsif ( $in == 6 ) {
	search(2);
    } elsif ( $in == 7 ) {
	search(3);
    } elsif ( $in == 8 ) {
	insertRecord();
    } elsif ( $in == 9 ) {
	deleteRecord(1);
    } elsif ( $in == 10 ) {
	deleteRecord(2);
    } elsif ( $in == 0 ) {
	print "Exiting...\n";
	last;
    } else {
	print "Invalid option.\n"
    }
	
}

### Main ###

while ( 1 ) {
    menu();
}
