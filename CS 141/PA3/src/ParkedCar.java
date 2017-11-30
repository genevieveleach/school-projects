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
public class ParkedCar {
    //private fields for a parked car - make, model, color,
    //license number and minutes parked
    private String make;
    private String model;
    private String color;
    private String licenseNumber;
    private int minutesParked;

    //first constructor involves passing each individual argument
    public ParkedCar(String mk, String mod, String col, String lic, int min) {
      make = mk;
      model = mod;
      color = col;
      licenseNumber=lic;
      minutesParked=min;
    }

    //this is the copy constructor, which copies the fields from the argument
    public ParkedCar(ParkedCar car2) {
      make=car2.make;
      model=car2.model;
      color=car2.color;
      licenseNumber=car2.licenseNumber;
      minutesParked=car2.minutesParked;
    }

    //mutates the make of car
    public void setMake(String m) {
      make=m;
    }

    //mutates the model of car
    public void setModel(String m) {
      model=m;
    }

    //mutates the color of car
    public void setColor(String c) {
      color=c;
    }

    //mutates the licenseNumber of car
    public void setLicenseNumber(String l) {
      licenseNumber=l;
    }

    //mutates the minutesparked of car
    public void setMinutesParked(int m) {
      minutesParked=m;
    }

    //accesses the make of car
    public String getMake() {   
        return make;
    }

    //accesses the model of car
    public String getModel() {
        return model;
    }

    //accesses the color of car
    public String getColor() {
        return color;
    }

    //accesses the licenseNumber of car
    public String getLicenseNumber() {
        return licenseNumber;
    }

    //accesses the minutesParked of car
    public int getMinutesParked() {
        return minutesParked;
    }

    //returns the appropriate information of the car object in string form
    public String toString() {
        
        return "Make: " + make + "/nModel: " + model + "/nColor: " 
               + color + "/nLicense Number: " + licenseNumber 
               + "/nMinutes Parked: " + minutesParked;
    }
}
