// Genevieve Leach
// CS141, Section 2
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

//import DecimalFormat to ensure that Fine amounts are displayed
//with 2 decimal place accuracy

import java.text.DecimalFormat;


//class header
public class ParkingTicket {
    //private fields of the car, officer, minutes, fine
    private ParkedCar car;
    private PoliceOfficer officer;
    private int minutes;
    private double fine;

    //static constant members of the class
    public static final double BASE_FINE = 25.0;
    public static final double HOURLY_FINE = 10.0;

    //constructor initializing fields by passing in each value
    public ParkingTicket(ParkedCar aCar, PoliceOfficer anOfficer, int min) {
    //uses setCar and setOfficer methods to deep copy instead of shallow  
      setCar(aCar);
      setOfficer(anOfficer);
      minutes=min;
    }

    //copy constructor, which copies the fields of the argument object
    public ParkingTicket(ParkingTicket ticket2) {
   //uses setCar and setOfficer methods to deep copy instead of shallow
      setCar(ticket2.getCar());
      setOfficer(ticket2.getOfficer());
      minutes=ticket2.minutes;
    }

    //determines the fine based on the minutes over the time limit
    private void calculateFine() {
    // minutes is already in minutes over, so assign base fine
      fine = BASE_FINE;
   // use a for loop to add hourly fine for each hour over
      for(double i = 0; i< minutes/60; i++)
      {
         fine+= HOURLY_FINE;
      }
    }

    //mutates car
    public void setCar(ParkedCar c) {
   //uses getX methods to deep copy instead of shallow
      car=new ParkedCar(c.getMake(), c.getModel(), c.getColor(),
                        c.getLicenseNumber(), c.getMinutesParked());
    }

    //mutates officer
    public void setOfficer(PoliceOfficer o) {
   //uses getX methods to deep copy instead of shallow
      officer=new PoliceOfficer(o.getName(), o.getBadgeNumber());
    }

    //mutates minutes
    public void setMinutes(int m) {
      minutes=m;
    }

    //accesses the car
    public ParkedCar getCar() {
        return car;
    }

    //accesses officer
    public PoliceOfficer getOfficer() {
        return officer;
    }

    //accesses fine
    public double getFine() {
        calculateFine();
        return fine;
    }

    //returns the important information of the ticket in String format
    public String toString() {
        return car.toString() + "/nFine: " + fine + officer.toString();
    }


}
