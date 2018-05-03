// Genevieve Leach
// CS 431
// 11 February 2018
// Project 1: Schedulers

To run the program:

FIRST make sure that the "Leach_Genevieve_431p1/resources/" folder has the test data you want to use.

In the terminal, navigate to the "Leach_Genevieve_431p1/src/" folder.
In the terminal, compile the program using the command "javac Scheduler.java",
and then run using the command "java Scheduler [args]" where [args] is a space separated list of the files with test data. (no brackets)
example: java Scheduler testfile1.txt testfile2.txt

The output .csv files will be in the "Leach_Genevieve_431p1/output/" folder. The sample results used in the report will be included in the .zip

If you want to change the SWITCH_TIME variable, change its value in SchedulerAlg.java.
If you want to change any time quanta, its value is passed to the Lottery and RoundRobin constructors.