// Genevieve Leach
// CS 141, Section 2
// Lab 2
// 21 January 2016

public class ArraySum
{
   public static void main(String[] args)
   {
      int[][] matrix={ {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1} };
      int sum = 0;
      for(int i=0; i<3; i++)
      {
         for(int j=0; j<4; j++)
         {
            sum+=matrix[i][j];
         }
      }
      System.out.println("The sum is " + sum);
   }
}
