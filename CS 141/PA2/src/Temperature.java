// Genevieve Leach
// CS 141, Section 2
// Programming Assignment 2
// 6 February 2016

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

import java.text.DecimalFormat;

public class Temperature
{

   public double degrees;

   public char scale;

   /**
    * No args constructor for temperature
    */
   public Temperature()
   {
      degrees=0.0;
      scale='C';
   }

   /**
    * Constructor for temperature given the value for degrees
    * @param degrees the initial value to set degrees to
    */
   public Temperature(double degrees)
   {
      this.degrees=degrees;
      scale='C';
   }

   /**
    * Constructor for temperature given the value for scale, set to C if invalid
    * character
    * @param scale the initial value to set scale to
    */
   public Temperature(char scale)
   {
      degrees=0.0;
      this.scale=scale;
   // to check for a valid scale value (and set if it's not)
      setScale(this.scale);
   }

   /**
    * Constructor for temperature given the value for degrees and scale
    * @param degrees the initial value to set degrees to
    * @param scale the initial value to set scale to
    */
   public Temperature(double degrees, char scale)
   {
      this.degrees=degrees;
      this.scale=scale;
   // to check for a valid scale value (and set if it's not)
      setScale(this.scale);
   }

   /**
    * @return the degrees field in the form of degrees Fahrenheit
    */
   public double getTemperatureFahrenheit()
   {
     if (scale=='C')
     {
        degrees=((9*degrees)/5)+32;
        return degrees;
     }
     else if(scale=='F')
     {
        return degrees;
     }
     else
     { 
        Double badResult = null;
        return badResult;
     }
   }

   /**
    * @return the degrees field in the form of degrees Celsius
    */
   public double getTemperatureCelsius()
   {
      if(scale=='F')
      {
         degrees=5*(degrees-32)/9;
         return degrees;
      }
      else if(scale=='C')
         return degrees;
      else
      {
         Double badResult = null;
         return badResult;
      }
   }

   /**
    * sets the degrees to the value supplied
    * @param degrees the new value to set degrees to
    */
   public void setDegrees(double degrees)
   {
      this.degrees=degrees;
   }

   /**
    * sets the scale to the value supplied
    * @param scale the new value to set the scale to
    */
   public void setScale(char scale)
   {
       switch(scale)
      {
         case 'c':
         {
            this.scale='C';
            break;
         }

         case 'C':
         {
            this.scale='C';
            break;
         }

         case 'f':
         {
            this.scale='F';
            break;
         }

         case 'F':
         {
            this.scale='F';
            break;
         }

         default:
         this.scale='C';
      }
   }

   /**
    * sets the temperature and scale to the values supplied
    * @param degrees the new value to set degrees to
    * @param scale the new value to set the scale to
    */
   public void setDegreesScale(double degrees, char scale)
   {
      this.degrees=degrees;
      this.scale=scale;   
// to check for a valid scale value (and set if it's not)
      setScale(this.scale);
   }

   /**
    * checks whether this temperature is equal to temperature 2
    * @param temp2 the temperature to compare this Temperature to
    * @return
    */
   public boolean equals(Temperature temp2)
   {
      boolean status;
      double degreesF, degreesC;
   // check to see if scale is undefined
      if (scale == 0)
      {
         Boolean badResult = null;
         return badResult;
      }
   //first check to see if both temp scales are the same
      if (scale == temp2.scale)
      {
         if (degrees == temp2.degrees) 
         {
    //if both temp values are the same, mark as equal
            status = true;
	 } 
         else 
         {
   //else, assume they're false and mark as inequal
	    status = false;
         }
      } 
      else if ((scale == 'C') && (temp2.scale == 'F')) 
      {
   //if temp1's scale is C but temp2's scale is F
   //convert temp's temp value to match temp2's scale

         degreesF = ((9 * degrees) / 5) + 32;
         degrees = degreesF;
   //now check to see if the two temp values equal
         if (degrees == temp2.degrees)
         {
   //if both temp values are the same, mark as equal
            status = true;
	 } 
         else 
         {
   //else, assume they're inequal and mark as false
            status = false;
         }
      }
      else 
      {
   //else, assume temp1's scale if F but temp2's scale is C
   //convert temp1's temp value to match temp2's scale
         degreesC = 5 * (degrees - 32) / 9;
         degrees = degreesC;
   //now check to see if the two temp values equal
         if (degrees == temp2.degrees) 
         {
   //if both temp values are the same, mark as equal
            status = true;
         }
         else 
         {
   //else, assume they're false and mark as inequal
            status = false;
         }
      }
      return status;
   }

   /**
    * checks whether this temperature is greater than temperature 2
    * @param temp2 the temperature to compare this Temperature to
    * @return whether this temperature is greater than temperature 2
    */
   public boolean greaterThan(Temperature temp2)
   {
      boolean status;
      double degreesF, degreesC;
   //check to see if scale is undefined
      if(scale == 0)
      {
         Boolean badResult=null;
         return badResult;
      }
   //first check to see if both temp scales are the same
      if (scale == temp2.scale)
      {
   //if they are, check to see if temp1 is greater than temp2
         if (degrees > temp2.degrees)
         {
   //if it is, mark the status as true
            status = true;
         } 
         else 
         {
   //else, assume it's less than and mark status as false
            status = false;
         }
      }
      else if ((scale == 'C') && (temp2.scale == 'F')) 
      {
   //if temp1's scale is C but temp2's scale is F
   //convert temp1's temp value to match temp2's scale
         degreesF = ((9 * degrees) / 5) + 32;
         degrees = degreesF; 
   //now check to see if temp1 is greater than temp2
         if (degrees > temp2.degrees) 
         {
   //if it is, mark the status as true
            status = true;
         } 
         else 
         {
   //else, assume it's less than and mark status as false
            status = false;
         }
      } 
      else 
         {
   //else, assume temp1's scale if F but temp2's scale is C
   //convert temp1's temp value to match temp2's scale
         degreesC = 5 * (degrees - 32) / 9;
         degrees = degreesC;
   //now check to see if temp1 is greater than temp2
         if (degrees > temp2.degrees) 
         {
   //if it is, mark the status as true
            status = true;
         } 
         else
         {
   //else, assume it's less than and mark status as false
            status = false;
         }
      }
      return status;
   }

   /**
    * checks whether this temperature is less than temperature 2
    * @param temp2 the temperature to compare this Temperature to
    * @return whether this temperature is less than temperature 2
    */
   public boolean lessThan(Temperature temp2)
   {
      boolean status;
      double degreesF, degreesC;
   //check to see if scale is undefined
      if(scale == 0)
      {
         Boolean badResult=null;
         return badResult;
      }
   //first check to see if both temp scales are the same
      if (scale == temp2.scale)
      {
         if (degrees < temp2.degrees) 
         {
   //if temp1 is less than temp2, mark the status as true
            status = true;
         }
         else 
         {
   //else, assume it's greater than and mark status as false
            status = false;
         }
      } 
      else if ((scale == 'C') && (temp2.scale == 'F'))
      {
   //if temp1's scale is C but temp2's scale is F
   //convert temp1's temp value to match temp2's scale
         degreesF = ((9 * degrees) / 5) + 32;
         degrees = degreesF;
   //now check to see if temp1 is less than temp2
         if (degrees < temp2.degrees)
         {
   //if it is, mark the status as true
             status = true;
         } 
         else
         {
   //else, assume it's greater than and mark status as false
            status = false;
         }
      }
      else 
      {
   //else, assume temp1's scale if F but temp2's scale is C
   //convert temp1's temp value to match temp2's scale
         degreesC = 5 * (degrees - 32) / 9;
         degrees = degreesC;
   //now check to see if temp1 is less than temp2
         if (degrees < temp2.degrees) 
         {
      //if it is, mark the status as true
            status = true;
         } 
         else 
         {
      //else, assume it's greater than and mark status as false
            status = false;
         }
      }
      return status;
   }

   @ Override
   /**
    * this is already done for you
    * @return the Temperature object as a String of the format
    * #,##0.0 C or #,##0.0 F
    */
   public String toString()
   {
      DecimalFormat formatter = new DecimalFormat("#,##0.0");
      String value = formatter.format(degrees) + " " + scale;
      return value;
   }
}
