// Genevieve Leach
// CS140, Section 1
// Project 2 - Time Conversion
// 11 October 2015

import java.util.Scanner;

public class TimeConversionToSecond
{
   public static void main(String[] args)
   {
      Scanner kb=new Scanner(System.in);
      System.out.print("Enter hours: ");
      int hours=kb.nextInt();
      System.out.print("Enter minutes: ");
      int minutes=kb.nextInt();
      System.out.print("Enter seconds: ");
      int seconds=kb.nextInt();

      int totalSeconds=3600*hours+60*minutes+seconds;
      
      System.out.println(hours+" hours, "+minutes
                         +" minutes, "+seconds
                         +" seconds is equivalent to "
                         +totalSeconds+" seconds.");
   }
}
