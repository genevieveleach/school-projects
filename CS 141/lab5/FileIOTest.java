import java.io.*;

public class FileIOTest
{
   public static void main(String[] args)
   {
      FileIO IO = new FileIOSubClass();
      System.out.println("I will demonstrate fileRead.");
      try
      {
         IO.fileRead("animals.txt");
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
      }

      System.out.println();

      System.out.println("I will demonstrate fileRead with a File.");
      try
      {
         File file = new File("animals.txt");
         IO.fileRead(file);
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
      }

      System.out.println();

      System.out.println("I will demonstrate fileWrite.");
      String[] stArr = {"Boar", "Snail", "Roly Poly", "Armadillo",
                        "Jaguar", "End of additions."};
      try
      {
         IO.fileWrite("animals.txt", stArr);
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
      }

   }
}
