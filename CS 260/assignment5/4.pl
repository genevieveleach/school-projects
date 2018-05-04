#!/usr/bin/perl
use warnings;
use strict;
my $date = `date`;
my @thedate = split / /, $date;
foreach my $line (@thedate) 
{
    print "$line\n";
}
