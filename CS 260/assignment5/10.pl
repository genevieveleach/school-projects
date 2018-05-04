#!/usr/bin/perl
use warnings;
use strict;

if ( $#ARGV+1 != 2 ) {
    die "Wrong args. Please input 2 integers as a command line argument.\n";
}
my $num1 = $ARGV[0];
my $num2 = $ARGV[1];
sub nextOP {
    print "\nWould you like to do another operation with the same integers (enter s or S) with different integers (enter d or D) or exit (enter e or E)?\n";
    print "Please enter a choice: ";
    my $choice = <STDIN>;
    chomp($choice);
    if ( $choice eq "s" || $choice eq "S" ) {
	menu();
    } elsif ( $choice eq "d" || $choice eq "D" ) {
	changeNum();
    } elsif ( $choice eq "e" || $choice eq "E" ) {
	exit;
    } else {
	print "Invalid choice. Please choose again. \n\n";
	nextOP();
    }
}

sub menu {
    print "\n*** MENU ***\n";
    print "Add the numbers (Enter a or A)\nSubtract the numbers (Enter s or S)\n";
    print "Multiply the numbers (enter m or M)\nDivide the numbers (enter d or D)\n";
    print "Exit (select e or E)\n";
    print "Please enter a choice: ";
    my $choice = <STDIN>;
    chomp($choice);
    if ( $choice eq "a" || $choice eq "A" ) {
	print "$num1 + $num2 = ";
	print $num1 + $num2;
	print "\n";
	nextOP();
    } elsif ( $choice eq "s" || $choice eq "S" ) {
	print "$num1 - $num2 = ";
        print $num1 - $num2;
        print "\n";
	nextOP();
    } elsif ( $choice eq "m" || $choice eq "M" ) {
	print "$num1 * $num2 = ";
        print $num1 * $num2;
        print "\n";
	nextOP();
    } elsif ( $choice eq "d" || $choice eq "D" ) {
	print "$num1 / $num2 = ";
        print $num1 / $num2;
        print "\n";
	nextOP();
    } elsif ( $choice eq "e" || $choice eq "E" ) {
	exit;
    } else {
	print "Invalid choice. Please choose again.\n\n";
	menu();
    }
}

sub changeNum {
    print "Enter first number: ";
    $num1 = <STDIN>;
    chomp($num1);
    print "Enter second number: ";
    $num2 = <STDIN>;
    chomp($num2);
    menu();
}

menu();
