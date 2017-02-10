// Genevieve Leach
// CS141, Section 2
// Programming Assignment 4
// 8 March 2016

/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 141
 * Programming Assignment 4
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
public class CargoPlane extends Plane{
	//Private fields
	private int cargoCapacity;
	
	 //Constructor
	 public CargoPlane(String model, String year, String mileage, int cargoCapacity) {
         //Use the constructor for the super class to set variables.
         //Alternatively, could use super.setX methods.
               super(model, year, mileage);
               this.cargoCapacity = cargoCapacity;
	    }
		
               
	    public int getCargoCapacity() {
	       return cargoCapacity;
	    }

	    public void setCargoCapacity(int cargoCapacity) {
	       this.cargoCapacity = cargoCapacity;
	    }
		
		//Method for checking if the weight is too heavy or not
	    public void fillCargo(int weight) throws IllegalSizeException{
	       if(weight>cargoCapacity)
                  throw new IllegalSizeException(weight);
	    }
	    
	    @Override
	    public String toString() {
	        return super.toString() + "\nCargo Capacity: " + cargoCapacity;
	    }

}
