// Genevieve Leach
// CS 140, Section 1
// Project 4 - Slot Machine Simulation
// 9 November 2015

import java.util.*;

public class SlotMachineSimulation
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      Random randInt = new Random();
      
      final int MAX_RAND_NUMBER=6;
      
      Double winnings=0.0, totalWinnings=0.0, totalBet=0.0, betAmount=0.0;
      String playAgain, pic1="", pic2="", pic3="";
      int value1, value2, value3;

      System.out.println("Welcome to the Slot Machine Simulation.");
      System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
      do
      {
         System.out.print("\nEnter the amount you would like to bet: $");
         betAmount = kb.nextDouble();
         while (betAmount<=0)
         {
            System.out.print("You must bet something! Please enter a valid bet: $");
            betAmount = kb.nextDouble();
         }
         totalBet+=betAmount;
         System.out.println();
         value1 = randInt.nextInt(MAX_RAND_NUMBER);
         value2 = randInt.nextInt(MAX_RAND_NUMBER);
         value3 = randInt.nextInt(MAX_RAND_NUMBER);
         switch(value1)
         {
            case 0:
               pic1="Cherries";
               break;
            case 1:
               pic1="Oranges";
               break;
            case 2:
               pic1="Plums";
               break;
            case 3:
               pic1="Bells";
               break;
            case 4:
               pic1="Melons";
               break;
            case 5:
               pic1="Bars";
               break;
         }
         switch(value2)
         {
            case 0:
               pic2="Cherries";
               break;
            case 1:
               pic2="Oranges";
               break;
            case 2:
               pic2="Plums";
               break;
            case 3:
               pic2="Bells";
               break;
            case 4:
               pic2="Melons";
               break;
            case 5:
               pic2="Bars";
               break;
         }
         switch(value3)
         {
            case 0:
               pic3="Cherries";
               break;
            case 1:
               pic3="Oranges";
               break;
            case 2:
               pic3="Plums";
               break;
            case 3:
               pic3="Bells";
               break;
            case 4:
               pic3="Melons";
               break;
            case 5:
               pic3="Bars";
               break;
         } 
         System.out.printf("- %s -- %s -- %s -\n", pic1, pic2, pic3);
         System.out.println();
         if (value1==value2 &&  value2==value3)
         {
            winnings = betAmount*3;
            System.out.print("Wow! All three match!\nThat triples your" 
                             +" bet!\nYou win $"); 
            System.out.printf("%,.2f\n", winnings);
            totalWinnings += winnings;
         }
         else if (value1==value2 || value1==value3 || value2==value3)  
         {
            winnings = betAmount*2;
            System.out.print("Great! Two match.\nThat doubles your bet!"
                             +"\nYou win $");
            System.out.printf("%,.2f\n", winnings);
            totalWinnings+=winnings;
         }
         else
         {
            System.out.print("Sorry, none match...\nYou win $");
            System.out.printf("%,.2f\n", betAmount*0);
         }
         System.out.println();
         System.out.println("Would you like to play again?");
         kb.nextLine();
         System.out.print("Enter Y for yes or N for no: ");
         playAgain = kb.nextLine();
         while (!playAgain.equalsIgnoreCase("Y") &&
               !playAgain.equalsIgnoreCase("N"))
         {
            System.out.print("Invalid answer!\nEnter Y for yes or N for"
                             + " no: ");
            playAgain = kb.nextLine();
         }            
      } while (playAgain.equalsIgnoreCase("Y"));
      System.out.printf("\nYou bet a total of $%,.2f", totalBet);
      System.out.printf("\nYou won a total of $%,.2f\n", totalWinnings);
   }
}
