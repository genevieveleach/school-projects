#!/usr/bin/perl
use warnings;
use strict;

if ( $#ARGV+1 != 2 ) {
    print "Wrong amount of args: $#ARGV. Please input 2 positive integers.\n";
    exit;
}
if ( $ARGV[0] < 0 || $ARGV[1] < 0 ) {
    print "Integers must be positive. Please input 2 positive integers.\n"
}

my $greater = $ARGV[0];
my $lesser = $ARGV[1];

if ( $ARGV[0] < $ARGV[1] ) {
    $greater = $ARGV[1];
    $lesser = $ARGV[0];
}

my $num = $greater;
my $den = $lesser;

#GCD
my $remain = $num % $den;

while ( $remain != 0 ) {
    $num = $den;
    $den = $remain;
    $remain = $num % $den;
}

my $gcd = $den;

#LCM
my $lcm = $greater * $lesser;
$lcm = $lcm / $gcd;

print "The GCD of $greater and $lesser is $gcd\nThe LCM of $greater and $lesser is $lcm\n";
