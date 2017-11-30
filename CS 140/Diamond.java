// Genevieve Leach
// CS 140, Section 1
// Project 5 - Display Diamonds Program
// 18 November 2015

import java.util.Scanner;

public class Diamond
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      String answer;
      int size = -1;
      do
      {
         System.out.print("Enter diamond size (\"short\", \"average\", or"
                          + " \"tall\"): ");
         answer=kb.nextLine();
         size=checkSize(answer);
      } while(size==-1);
      System.out.print("Enter pattern character: ");
      char ch=kb.nextLine().charAt(0);
      System.out.println();
      displayDiamond(size,ch);
      System.out.println();
      displayHollowDiamond(size,ch);
   }

   public static int checkSize(String size)
   {
      int height;
      if(size.equalsIgnoreCase("short"))
         height=6;
      else if(size.equalsIgnoreCase("average"))
         height=12;
      else if(size.equalsIgnoreCase("tall"))
         height=24;
      else 
         height=-1;
      return height;   
   }

   public static void displayDiamond(int size, char ch)
   {
      for (int i = 1; i < size; i += 2) 
      {
         for (int j = 0; j < (size-1) - i / 2; j++)
            System.out.print(" ");
         for (int j = 0; j < i; j++)
            System.out.print(ch);
      System.out.print("\n");
      }
      for (int i = (size-1); i > 0; i -= 2)
      {
         for (int j = 0; j < (size-1) - i / 2; j++)
            System.out.print(" ");
         for (int j = 0; j < i; j++)
            System.out.print(ch);
      System.out.print("\n");
      }
   }

   public static void displayHollowDiamond(int size, char ch)
   {
      for (int i = 1; i < size; i += 2) 
      {
         for (int j = 0; j < (size-1) - i / 2; j++)
            System.out.print(" ");
         for (int j = 0; j < i; j++)
         {
            if (j==i-1 || j==(i-1)/i) 
               System.out.print(ch);
            else 
               System.out.print(" ");
         }
      System.out.print("\n");
      }
      for (int i = (size-1); i > 0; i -= 2)
      {
         for (int j = 0; j < (size-1) - i / 2; j++)
            System.out.print(" ");
         for (int j = 0; j < i; j++)
         {
            if (j==i-1 || j==(i-1)/i)
               System.out.print(ch);
            else
               System.out.print(" ");
         }
      System.out.print("\n");
      }
   }
}
