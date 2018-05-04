import java.util.Arrays;
import java.util.Scanner;

public class SPPGauss {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);

    int numEquations = 0;
/*    while(numEquations <= 0 || numEquations >= 10) {
      System.out.print("How many linear equations would you like to solve?: ");
      numEquations = kb.nextInt();
      kb.nextLine();
      if (numEquations > 10 || numEquations <= 0) {
        System.out.println("Invalid amount. Please enter coefficients number between 1 and 10.");
      }
    }*/
    //TODO: Write user input/file input
/*    String choice = "";
    while(!choice.equalsIgnoreCase("enter") || !choice.equalsIgnoreCase("file")) {
      System.out.println("Would you like to enter the coefficients of each equation manually or provide coefficients file name?");
      System.out.print("Please type \"enter\" or \"file\" for your choice: ");
      choice = kb.nextLine();
      if (!choice.equalsIgnoreCase("enter") || !choice.equalsIgnoreCase("file")) {
        System.out.println("Invalid option.");
      }
    }

    if(choice.equalsIgnoreCase("enter")) {
      for (int i = 0; i < numEquations; i++) {
        System.out.println("Please enter the coefficients of the equation, separated by spaces.");
      }
    } else {

    }*/

    // separate input into matrices
/*    double[][] coefficients = new double[numEquations][numEquations];
    double[] rightHandSide = new double[numEquations];*/

    double[][] coefficients = {{2,3,-1}, {3,1,2}, {1,0,4}};
    double[] rightHandSide = {4,5,1};
    numEquations = coefficients.length;
    double[] results = new double[numEquations];
    int[] indices = new int[numEquations];

    gauss(numEquations, coefficients, indices);
    solve(numEquations, coefficients, indices, rightHandSide, results);

    // print solution
    char variable = (char)(90-numEquations+1);
    for (double aX : results) {
      System.out.println(variable + ": " + aX);
      variable++;
    }
  }

  private static void gauss(int numEquations, double[][] coefficients, int[] indices) {
    //initialize variables
    int i = 0;
    int j = 0;
    int k = 0;
    double r = 0.0;
    double rmax = 0.0;
    double smax = 0.0;
    double xmult = 0.0;
    double[] s = new double[numEquations];

    for(i = 0; i < numEquations; i++) {
      indices[i] = i;
      for (j = 0; j < numEquations; j++) {
        smax = Math.max(smax, Math.abs(coefficients[i][j]));
      }
      s[i] = smax;
    }
    for (k = 0; k < numEquations - 1; k++) {
      rmax = 0;
      for (i = k; i < numEquations; i++) {
        r = Math.abs(coefficients[indices[i]][k] / s[indices[i]]);
        if (r > rmax) {
          rmax = r;
          j = i;
        }
      }
      int temp = indices[j];
      indices[j] = indices[k];
      indices[k] = temp;
      for (i = k + 1; i < numEquations; i++) {
        xmult = coefficients[indices[i]][k] / coefficients[indices[k]][k];
        coefficients[indices[i]][k] = xmult;
        for (j = k + 1; j < numEquations; j++) {
          coefficients[indices[i]][j] = coefficients[indices[i]][j] - (xmult * coefficients[indices[k]][j]);
        }
      }
    }
  }

  private static void solve(int numEquations, double[][] coefficients, int[] indices, double[] rightHandSide, double[] results) {
    int i;
    int k;
    double sum;
    for (k = 0; k < numEquations - 1; k++) {
      for (i = k + 1; i < numEquations; i++) {
        rightHandSide[indices[i]] = rightHandSide[indices[i]] - (coefficients[indices[i]][k] * rightHandSide[indices[k]]);
      }
    }
    for(int index = 0; index < numEquations; index++){
      results[index] = rightHandSide[indices[index]] / coefficients[indices[index]][index];
    }
    for (i = numEquations - 1; i > 0; i--) {
      sum = rightHandSide[indices[i]];
      for (int j = i + 1; j < numEquations; j++) {
        sum = sum - (coefficients[indices[i]][j] * results[j]);
      }
      results[i] = sum / coefficients[indices[i]][i];
    }
  }
}
