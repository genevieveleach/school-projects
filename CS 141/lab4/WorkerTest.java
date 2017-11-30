public class WorkerTest
{
   public static void main (String[] args)
   {
      ProductionWorker employee1 = new ProductionWorker("John Smith",
                                       "123-A", "11-15-2005", 1, 16.50);
      ProductionWorker employee2 = new ProductionWorker();
      employee2.setName("Joan Jones");
      employee2.setNumber("222-L");
      employee2.setHireDate("12-12-2005");
      employee2.setShift(2);
      employee2.setHourlyPayRate(18.50232);
      ProductionWorker employee3 = new ProductionWorker("Tony Gaddis",
                                       "14DA5", "1-23-2006", 4, 19.501);
      System.out.println("Here's the first production worker.");
      System.out.println(employee1.toString());
      System.out.println();
      System.out.println("Here's the second production worker.");
      System.out.println(employee2.toString());
      System.out.println();
      System.out.println("Here's the third production worker.");
      System.out.println(employee3.toString());
   }
}
