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
        System.out.println("Invalid amount. Please enter a number between 1 and 10.");
      }
    }*/
    //TODO: Write user input/file input
/*    String choice = "";
    while(!choice.equalsIgnoreCase("enter") || !choice.equalsIgnoreCase("file")) {
      System.out.println("Would you like to enter the coefficients of each equation manually or provide a file name?");
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
/*    double[][] a = new double[numEquations][numEquations];
    double[] b = new double[numEquations];*/

    double[][] a = {{2,3,-1}, {3,1,2}, {1,0,4}};
    double[] b = {4,5,1};
    numEquations = a.length;
    System.out.println(numEquations);
    double[] x = new double[numEquations];
    int[] l = new int[numEquations];

    gauss(numEquations, a, l);
    solve(numEquations, a, l, b, x);

    // print solution
    char variable = (char)(90-numEquations+1);
    for (double aX : x) {
      System.out.println(variable + ": " + aX);
      variable++;
    }
  }

  private static void gauss(int n, double[][] a, int[] l) {
    //initialize variables
    int i = 0;
    int j = 0;
    int k = 0;
    double r = 0.0;
    double rmax = 0.0;
    double smax = 0.0;
    double xmult = 0.0;
    double[] s = new double[n];

    for(i = 0; i < n; i++) {
      l[i] = i;
      for (j = 0; j < n; j++) {
        smax = Math.max(smax, Math.abs(a[i][j]));
      }
      s[i] = smax;
    }
    for (k = 0; k < n - 2; k++) {
      rmax = 0;
      for (i = k; i < n; i++) {
        r = Math.abs(a[l[i]][k] / s[l[i]]);
        if (r > rmax) {
          rmax = r;
          j = i;
        }
      }
      int temp = l[j];
      l[j] = l[k];
      l[k] = temp;
      for (i = k + 1; i < n; i++) {
        xmult = a[l[i]][k] / a[l[k]][k];
        a[l[i]][k] = xmult;
        for (j = k + 1; j < n; j++) {
          a[l[i]][j] = a[l[i]][j] - xmult * a[l[k]][j];
        }
      }
    }
  }

  private static void solve(int n, double[][] a, int[] l, double[] b, double[] x) {
    int i;
    int k;
    double sum;
    for (k = 0; k < n - 2; k++) {
      for (i = k + 1; i < n; i++) {
        b[l[i]] = b[l[i]] - a[l[i]][k] * b[l[k]];
      }
    }
    x[n-1] = b[l[n-1]] / a[l[n-1]][n-1];
    for (i = n - 2; i >= 0; i--) {
      sum = b[l[i]];
      for (int j = i + 1; j < n; j++) {
        sum = sum - a[l[i]][j] * x[j];
      }
      x[i] = sum / a[l[i]][i];
    }
  }
}
