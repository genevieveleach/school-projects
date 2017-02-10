// Genevieve Leach
// CS141, Section 2
// Programming Project 3
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
public class PoliceOfficer {
    //private fields for name and badgenumber
    private String name;
    private String badgeNumber;

    //constructor for policeofficer to initialize fields
    public PoliceOfficer(String n, String bn) {
      name=n;
      badgeNumber=bn;
    }

    //copy constructor for policeofficer, copies argument object's fields
    public PoliceOfficer(PoliceOfficer officer2) {
      name=officer2.name;
      badgeNumber=officer2.badgeNumber;
    }

    //mutates name
    public void setName(String n) {
      name=n;
    }

    //mutates badgenumber
    public void setBadgeNumber(String bn) {
      badgeNumber=bn;
    }

    //accesses name
    public String getName() {
        return name;
    }

    //accesses badgenumber
    public String getBadgeNumber() {
        return badgeNumber;
    }

    //performs a comparison of the car's parked minutes and
    //minutes purchased off of meter
    //issues parking ticket if appropriate
    public ParkingTicket patrol(ParkedCar car, ParkingMeter meter) {
     int minutesPurchased=meter.getMinutesPurchased();
     int minutesParked=car.getMinutesParked();
     if(minutesPurchased<minutesParked)
      {
         int minutesOver = minutesParked - minutesPurchased;
         ParkingTicket ticket = new ParkingTicket(car, this,
                                                  minutesOver);
         return ticket;
      }
      else
         return null;
    }

    //returns information about Officer in string format
    public String toString() {
        return "Officer Name: " + name + "/nBadge Number: " + 
               badgeNumber;
    }
}
