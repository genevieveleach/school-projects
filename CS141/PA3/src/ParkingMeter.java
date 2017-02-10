// Genevieve Leach
// CS 141, Section 2
// Programming Assignment 3
// 22 February 2016

/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 141
 * Programming Assignment 3
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
 * will be deducted from your assignment. Also, make sure you
 * follow coding conventions or you will be deducted points.
 *
 **/
 
//class header
public class ParkingMeter {
    //private field minutesPurchased
    private int minutesPurchased;

    //constructor to initialize the private field
    public ParkingMeter(int m) {
      minutesPurchased=m;
    }

    //mutator of minutesPurchased
    public void setMinutesPurchased(int m) {
      minutesPurchased=m;
    }

    //accessor of minutesPurchased
    public int getMinutesPurchased() {
        return minutesPurchased;
    }
}
