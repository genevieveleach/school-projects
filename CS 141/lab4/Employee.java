public class Employee
{
   private String employeeName;
   private String employeeNumber;
   private String hireDate;

   public Employee()
   {
      employeeName="";
      employeeNumber="";
      hireDate="";
   }

   public Employee(String name, String number, String date)
   {
      employeeName=name;
      employeeNumber=number.toUpperCase();
      hireDate=date;
      boolean valid = validateNumber(employeeNumber);
      if(valid==false)
      {
         employeeNumber="";
      }
   }

   public void setName(String name)
   {
      employeeName=name;
   }

   public void setNumber(String number)
   {
      employeeNumber=number;
      boolean valid = validateNumber(employeeNumber);
      if(valid==false)
      {
         employeeNumber="";
      }
   }

   public void setHireDate(String date)
   {
      hireDate=date;
   }

   public String getName()
   {
      return employeeName;
   }

   public String getNumber()
   {
      return employeeNumber;
   }

   public String getHireDate()
   {
      return hireDate;
   }

   public String toString()
   {
      if(employeeNumber=="")
      {
         return "Name: "+ employeeName + "\nEmployee Number: "
                + "INVALID EMPLOYEE NUMBER \nHire Date: " + hireDate;
      }
      else
      {
         return "Name: "+ employeeName + "\nEmployee Number: "
                + employeeNumber + "\nHire Date: " + hireDate;
      }
   }

   private boolean validateNumber(String n)
   {
      char[] c = new char[n.length()];
      for(int i = 0; i<n.length() ; i++)
      {
         c[i]=n.charAt(i);
      }
      if (c.length!=5)
      {
         return false;
      }
      for(int i = 0; i<3; i++)
      {
         if(c[i]<48 && c[i]>57)
         {
            return false;
         }
      }
      if(c[3]!=45)
         {
            return false;
         }
      if(c[4]<65 && c[4]>90)
         {
            return false;
         }
      return true;
   }
}
