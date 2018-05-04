#!/usr/bin/perl
use strict;
use warnings;
while (1)
{
    print "Are you OK?\n";
    my $input = <STDIN>;
    chomp($input);
    if ($input eq "y" || $input eq "Y")
    {
	print "Glad to hear it.\n";
	exit;
    }
    elsif ($input eq "n" || $input eq "N")
    {
	print "Sorry to hear you are not feeling well.\n";
	exit;
    } else
    {
	print "Invalid choice.\n";
    }
}
