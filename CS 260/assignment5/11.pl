#!/usr/bin/perl
use strict;
use warnings;

print "Enter an integer 1-10: ";
my $input = <STDIN>;
chomp($input);
while ( $input < 1 || $input > 10 ) {
    print "Incorrect range. Please enter a number 1-10: ";
    $input = <STDIN>;
    chomp($input);
}

#sum
my $sum = 0;
my $n = $input;
print "Sum of $input is ";
while ( $input >= 1 ) {
    $sum += $input;
    $input--;
}
$input = $n;
print "$sum(";
while ( $n != 1 ) {
    print "$n+";
    $n--;
}
print "1)\n";

#fact
my $fact = 1;
$n = $input;
print "Factorial of $n is ";
while ( $input >= 1) {
    $fact *= $input;
    $input--;
}
print "$fact(";
while ( $n != 1 ) {
    print "$n\*";
    $n--;
}
print "1)\n";
