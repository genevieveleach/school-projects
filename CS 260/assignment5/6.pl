#!/usr/bin/perl
use strict;
use warnings;

my $num = 0;
until ($num == 10)
{
    chomp($num);
    print "$num ";
    $num=`expr $num + 1`;
}
print "\n"
