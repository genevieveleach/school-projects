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
public class PrivateJet extends Plane{
	//private fields
	private int seating;
	private double cost;
	
	//Constructor
	public PrivateJet(String model, String year, String mileage, int seating, double cost) {
         //Use the constructor for the super class to set variables.
         //Alternatively, could use super.setX methods.
                super(model, year, mileage);
                this.seating = seating;
                this.cost = cost;
	    }

	    public int getSeating() {
	        return seating;
	    }

	    public void setSeating(int seating) {
	        this.seating = seating;
	    }
	    
	    public double getCost() {
	        return cost;
	    }

	    public void setCost(double cost) {
	        this.cost = cost;
	    }
	    
	    public void seatPassengers(int passengers) throws IllegalSizeException{
	         if(passengers>seating)
                    throw new IllegalSizeException(passengers);
		}
		
	    @Override
	    public String toString() {
	       return super.toString() + "\nSeating: " + seating + "\nCost: " + cost;
	    }

	
}
