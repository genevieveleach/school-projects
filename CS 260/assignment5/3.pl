#!/usr/bin/perl
use strict;
use warnings;
if (@ARGV < 1)
{
    die "Invalid args. Please put a single number 0-100 in the command line for your score.\n"
}
my $arg = $ARGV[0];
if ($arg >= 90 && $arg <= 100)
{
    print "You got A\n";
} elsif ($arg < 90 && $arg >= 80)
{
    print "You got B\n";
} elsif ($arg < 80 && $arg >= 70)
{
    print "You got C\n";
} elsif ($arg < 70 && $arg >= 60)
{
    print "You got D\n";
} elsif ($arg < 60 && $arg >= 0)
{
    print "You failed.\n";
} else
{
    die "Invalid args. Please put a single number 0-100 in the command line for your score.\n"
}
