import java.io.*;
import java.util.Scanner;

public class FileIOSubClass extends FileIO
{
   public void fileRead(String s) throws Exception
   {
      File inFile = new File(s);
      if(inFile.exists())
      {
         Scanner sc = new Scanner(inFile);
         while(sc.hasNextLine())
         {
            System.out.println(sc.nextLine());
         }
      }
      else
         throw new Exception("File not found.");
  }

   public void fileRead(File f) throws Exception
   {
      if(f.exists())
      {
         Scanner sc = new Scanner(f);
         while(sc.hasNextLine())
         {
             System.out.println(sc.nextLine());
         }
      }
      else
         throw new Exception("File not found.");
   }

   public void fileWrite(String s, String[] a) throws Exception
   {
      File file = new File(s);
      PrintWriter outfile = new PrintWriter(file);
      if(file.exists())
      {
         for(int i=0; i<a.length; i++)
         {
            outfile.println(a[i]);
         }
         System.out.println("Array written to file.");
         outfile.close();
      }
      else
         throw new Exception("IOException found.");
   }
}
