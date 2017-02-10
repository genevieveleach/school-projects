import java.text.DecimalFormat;

public class ProductionWorker extends Employee
{
   private int shift;
   private double hourlyPayRate;

   public ProductionWorker()
   {
       shift=1;
       hourlyPayRate=0.0;
   }

   public ProductionWorker(String n, String e, String d,int s,double h)
   {
       setName(n);
       setNumber(e);
       setHireDate(d);
       shift = s;
       hourlyPayRate = h;
   }

   public void setShift(int s)
   {
      shift=s;
   }

   public void setHourlyPayRate(double h)
   {
      hourlyPayRate = h;
   }

   public int getShift()
   {
      return shift;
   }

   public double getHourlyPayRate()
   {
      return hourlyPayRate;
   }

   public String toString()
   {
      DecimalFormat df = new DecimalFormat("#,###,##0.00");
      if(shift==1)
      {
         return super.toString() + "\nShift: Day" +
                 "\nHourly Pay Rate: $" + df.format(hourlyPayRate);
      }
      else if (shift==2)
      {
         return super.toString() + "\nShift: Night" +
                 "\nHourly Pay Rate: $" + df.format(hourlyPayRate);
      }
      else
      {
         return super.toString() + "\nShift: INVALID SHIFT NUMBER" + 
                "\nHourly Pay Rate: $" + df.format(hourlyPayRate);
      }
   }
}
