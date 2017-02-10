import java.io.*;
import java.util.Scanner;

public class Advice
{
   public static void main(String[] args) throws IOException
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("enter input filename: ");
      File file = new File(kb.nextLine());
      Scanner input = new Scanner(file);
      int counter=1;
      String adviceInput="";
      while(input.hasNextLine())
      {
         System.out.println(counter + ": " + input.nextLine());
         counter++;
      }
      FileWriter fileWriter = new FileWriter(file, true);
      PrintWriter output = new PrintWriter(fileWriter);
      boolean flag = true;
      while(flag==true)
      {
         System.out.print("enter advice (hit return on empty line to "
                          + "quit): ");
         adviceInput=kb.nextLine();
         if(adviceInput.equals(""))
         {
            flag=false;
            break;
         }
         else
         {
            output.println(adviceInput);
         }
      }
      kb.close();
      output.close();
      fileWriter.close();
      System.out.println("Thank you, bye!");
   }
}
