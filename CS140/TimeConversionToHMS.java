// Genevieve Leach
// CS 140, Section 1
// Project 2 - Time Conversion
// 11 October 2015

import java.util.Scanner;

public class TimeConversionToHMS
{
   public static void main(String[] args)
   {
      Scanner kb=new Scanner(System.in);
      System.out.print("Enter total seconds: ");
      int totalSeconds=kb.nextInt();
      
      int hours=totalSeconds/3600;
      int remainder=totalSeconds%3600;
      int minutes=remainder/60;
      int seconds=remainder%60;

      System.out.println(totalSeconds
                         +" seconds is equivalent to "
                         +hours+" hours "+minutes+" minutes "
                         +seconds+" seconds.");
   }
}
