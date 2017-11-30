// Genevieve Leach
// CS 141, Section 2
// 16 January 2016

/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 141-01/2
 * Programming Assignment 1
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * Feel free to include your private fields and methods.
 * But please make sure you do not change the signature
 * of the public methods provided. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 **/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Review
{
    //return new int[0] if the input is negative.
    public static void main(String[] args) throws IOException{
    // included throws IOException for output
       int[] array=loadArray();
       outputArray(array);
    }

    public static int[] loadArray() {
      Scanner kb = new Scanner(System.in);
      System.out.print("Please enter array size: ");
      int s = kb.nextInt(); // stored in variable to check for negative 
                            // input
      if(s<0)
      {
         int[] b = new int[0];
         return b;
      } // returned new int[0] as specified in instructions
      int[] a = new int[s]; // otherwise, proceed as normal.
      for(int i=0; i<a.length; i++)
      {
         System.out.print("enter element "+ (i+1) +  ": ");
         // i+1 so the user knows which number they are inputting
         // (1, 2, 3 as opposed to 0, 1, 2)
         a[i]=kb.nextInt();
      }
      return a; // make changes to the return type as needed
    }

    public static void outputArray(int[] a) throws IOException {
      PrintWriter output = new PrintWriter("data.txt"); // output file
      for(int i=0; i<a.length; i++) // printing each element of the array
      {
         output.println(a[i]);
      }
      output.close(); // must close output file!!
      System.out.println("data written to the file.");
      // lets user know that something happened/that data was actually
      // written.
    }
}

