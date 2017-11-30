// Genevieve Leach
// CS141, Section 2
// Programming Assignment 4
// 8 March 2016

/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 141
 * Programming Assignment 2
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 **/
public class IllegalSizeException extends Exception {

	/*Write a Constructor that takes the incorrect size and 
		displays the customized error message*/
    IllegalSizeException(int size){
      System.out.println(size + " is an invalid size.");
    }
}
