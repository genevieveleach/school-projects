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
 * Feel free to include your private fields and methods.
 * But please make sure you do not change the signature
 * of the public methods provided. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions or you will lose points.
 *
 **/
 
public class Plane {
	//Private fields
	private String model;
	private String year;
	private String mileage;
	
	 //Constructor
	 public Plane(String model, String year, String mileage) {
	    this.model = model;
            this.year = year;
            this.mileage = mileage;
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) {
	        this.model = model;
	    }

	    public String getYear() {
	        return year;
	    }

	    public void setYear(String year) {
	       this.year = year;
	    }
	    
	    public String getMileage() {
	        return mileage;
	    }

	    public void setMileage(String mileage) {
	       this.mileage = mileage;
	    }
		
		@Override
	    public String toString() {
		return "Model: " + model + "\nManufacturing Year: "
                       + year + "\nTotal Mileage: " + mileage;
	    }
}
