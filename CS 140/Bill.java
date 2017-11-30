// Genevieve Leach
// CS 140, Section 1
// Project 3 - Bill Statement
// 19 October 2015

import java.util.Scanner;

public class Bill
{
   public static void main(String[] args)
   {
      Scanner kb=new Scanner(System.in);
      
      System.out.print("Input name of item 1: ");
      String item1=kb.nextLine();
      System.out.print("Input quantity of item 1: ");
      int quantity1=kb.nextInt();
      if (quantity1<0 || quantity1>1000)
      {
         System.out.println("Invalid quantity, please enter quantity"
                         + " between 0 and 1,000.");
         System.exit(0);
      }
      System.out.print("Input price of item 1: ");
      double price1=kb.nextDouble();
      if (price1<0.0 || price1>10000.0)
      {
         System.out.println("Invalid price, please enter price between"
                         + " 0.0 and 10,000.0");
         System.exit(0);
      }
      kb.nextLine();
      System.out.print("\nInput name of item 2: ");
      String item2=kb.nextLine();
      System.out.print("Input quantity of item 2: ");
      int quantity2=kb.nextInt();
      if (quantity2<0 || quantity2>1000)
      {
         System.out.println("Invalid quantity, please enter quantity"
                            + "between 0 and 1,000.");
         System.exit(0);
      }
      System.out.print("Input price of item 2: ");
      double price2=kb.nextDouble();
      if (price2<0.0 || price2>10000.0)
      {
         System.out.println("Invalid price, please enter price"
                            + "between 0.0 and 10,000.0");
         System.exit(0);
      }
      kb.nextLine();
      System.out.print("\nInput name of item 3: ");
      String item3=kb.nextLine();
      System.out.print("Input quantity of item 3: ");
      int quantity3=kb.nextInt();
      if (quantity3<0 || quantity3>1000)
      {
         System.out.println("Invalid quantity, please enter quantity"
                            + "between 0 and 1,000.");
         System.exit(0);
      }
      System.out.print("Input price of item 3: ");
      double price3=kb.nextDouble();
      if (price3<0.0 || price3>10000.0)
      {
         System.out.println("Invalid price, please enter price"
                            + "between 0.0 and 10,000.0");
         System.exit(0);
      }
      
      String item="Item",
             quantity="Quantity",
             price="Price",
             total="Total",
             dash1="----",
             dash2="--------",
             dash3="-----";
      double total1=price1*quantity1,
             total2=price2*quantity2,
             total3=price3*quantity3;

      System.out.println("\nYour bill:\n");
      System.out.printf("%-30s%10s%10s%15s\n", item, quantity, price,
                        total);
      System.out.printf("%-30s%10s%10s%15s\n", dash1, dash2, dash3,
                        dash3);
      System.out.printf("%-30s%10d%10.2f%,15.2f\n", item1, quantity1,
                        price1, total1);
      System.out.printf("%-30s%10d%10.2f%,15.2f\n", item2, quantity2, 
                        price2, total2);
      System.out.printf("%-30s%10d%10.2f%,15.2f\n", item3, quantity3,
                        price3, total3);
      
      double subtotal=total1+total2+total3;
      final double SALES_TAX = 0.0875,
                   TOTAL_SALES_TAX = 1.0875;
      double tax=subtotal*SALES_TAX,
             totalAll=subtotal*TOTAL_SALES_TAX;
      
      System.out.print("\nSubtotal");
      System.out.printf("%,57.2f\n", subtotal);
      System.out.print("8.75% Sales Tax");
      System.out.printf("%,50.2f\n", tax);
      System.out.print("Total");
      System.out.printf("%,60.2f\n",totalAll);
   }
}
