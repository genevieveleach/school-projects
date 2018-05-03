import java.util.Scanner;

public class SPPGauss {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);

    int numEquations = 0;
    while(numEquations <= 0 || numEquations <= 10) {
      System.out.print("How many linear equations would you like to solve?: ");
      numEquations = kb.nextInt();
      kb.nextLine();
      if (numEquations > 10 || numEquations <= 0) {
        System.out.println("Invalid amount. Please enter a number between 1 and 10.");
      }
    }

    String choice = "";
    while(!choice.equalsIgnoreCase("enter") || !choice.equalsIgnoreCase("file")) {
      System.out.println("Would you like to enter the coefficients of each equation manually or provide a file name?");
      System.out.print("Please type \"enter\" or \"file\" for your choice: ");
      choice = kb.nextLine();
      if (!choice.equalsIgnoreCase("enter") || !choice.equalsIgnoreCase("file")) {
        System.out.println("Invalid option.");
      }
    }

    if(choice.equalsIgnoreCase("enter")) {

    } else {


    }
  }

  private static solve(double[][] matrix) {

  }

  private static void gauss(int n, double[][] a, double[] b) {
    for(int i = 0; i < n; i++) {

    }
  }
}
