// Genevieve Leach
// CS 420
// 25 October 2017
// Project 1: 8-Puzzle

To run:

Inside the terminal, navigate to the "Leach_Genevieve_420p1/src" folder.

Before running, there are a few things to consider:
1) Make sure that the boolean TESTING in both EightPuzzleDriver.java and AStar.java are set to false.
This is the default value, but TESTING makes the program run for 101 random puzzles and omit steps.
2) If you intend on using the file read feature, the file should be structured with valid (solvable) puzzles.
These puzzles must be one on each line, structured as either "012345678" or "0 1 2 3 4 5 6 7 8".
The file must also be in the same directory as all of the java files (Leach_Genevieve_420p1/src).
3) The program has little to no input sanitation in its current state; please enter only valid inputs, or the results
will not be as expected.
It will not check for repeated numbers or numbers outside the range of [0, 8] in a puzzle for user input or file read input.

In the terminal, compile the program using the command "javac EightPuzzleDriver.java",
and then run using the command "java EightPuzzleDriver".
The program will prompt for your input.