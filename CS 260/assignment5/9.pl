#!/usr/bin/perl
use strict;
use warnings;

sub do_square {
    my $square = $_[0] * $_[0];
    print "$square\n";
}

do_square($ARGV[0]);
