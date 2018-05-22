import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class SPPGauss {

  private static final boolean EXTRA_CREDIT = true;
  private static Scanner kb = new Scanner(System.in);

  public static void main(String[] args) {

    if (EXTRA_CREDIT) {
      // randomized/timed operation
      int numEquations = 10000;
      for (int i = 1; i <= 1; i++, numEquations = numEquations * 2) {
        System.out.println("Number of equations: " + numEquations);

        // generate system of equations
        double[][] coefficients = new double[numEquations][numEquations];
        double[] rightHandSide = new double[numEquations];
        generateRandomEquations(numEquations, coefficients, rightHandSide);

        // initialize other arrays
        double[] results = new double[numEquations];
        int[] indices = new int[numEquations];

        // solving process
        System.out.println("Solving...");
        long startTime = System.nanoTime();
        gauss(numEquations, coefficients, indices);
        solve(numEquations, coefficients, indices, rightHandSide, results);
        long endTime = System.nanoTime();

        // doesn't print out solution/matrix because of how many characters one would need
        System.out.println("Solved!");
        System.out.println("Time: " + (endTime - startTime) + " ns\n");
      }
    } else {
      // normal operation
      int numEquations = 0;
      while (numEquations <= 0 || numEquations >= 10) {
        System.out.print("How many linear equations would you like to solve?: ");
        numEquations = kb.nextInt();
        kb.nextLine();
        if (numEquations > 10 || numEquations <= 0) {
          System.out.println("Invalid amount. Please enter coefficients number between 1 and 10.");
        }
      }

      double[][] coefficients = new double[numEquations][numEquations];
      double[] rightHandSide = new double[numEquations];

      String choice;
      while (true) {
        System.out.println("Would you like to enter the coefficients of each equation manually or provide coefficients file name?");
        System.out.print("Please type \"enter\" or \"file\" for your choice: ");
        choice = kb.nextLine();

        if (choice.equalsIgnoreCase("enter")) {
          userInput(numEquations, coefficients, rightHandSide);
          break;
        } else if (choice.equalsIgnoreCase("file")) {
          fileInput(numEquations, coefficients, rightHandSide);
          break;
        } else {
          System.out.println("Invalid choice.");
        }
      }

      // initialize other arrays
      double[] results = new double[numEquations];
      int[] indices = new int[numEquations];

      // solving process
      System.out.println("Solving...");
      gauss(numEquations, coefficients, indices);
      solve(numEquations, coefficients, indices, rightHandSide, results);
      System.out.println("Solved!");

      // print solution
      char variable = setVariable(numEquations);
      for (double aX : results) {
        System.out.println(variable + ": " + aX);
        variable++;
      }
    }
  }

  // solver methods
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

    for (i = 0; i < numEquations; i++) {
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
    for (int index = 0; index < numEquations; index++) {
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

  // helper methods
  private static void generateRandomEquations(int numEquations, double[][] coefficients, double[] rightHandSide) {
    Random random = new Random();
    for (int i = 0; i < numEquations; i++) {
      for (int j = 0; j < numEquations; j++) {
        coefficients[i][j] = 100.0 * random.nextDouble();
      }
      rightHandSide[i] = 100.0 * random.nextDouble();
    }

  }

  // setter methods
  private static void userInput(int numEquations, double[][] coefficients, double[] rightHandSide) {
    char variable = setVariable(numEquations);
    for (int i = 0; i < numEquations; i++) {
      for (int j = 0; j < numEquations; j++, variable++) {
        System.out.print("Enter the " + variable + " coefficient for row " + i + ": ");
        coefficients[i][j] = kb.nextInt();
      }
      System.out.print("Enter the right hand side for row " + i + ": ");
      rightHandSide[i] = kb.nextInt();
      variable = setVariable(numEquations);
    }
  }

  private static char setVariable(int numEquations) {
    //sets default to x if numEquations < 3, else goes down whole alphabet
    if (numEquations < 3) {
      return (char) (90 - 3 + 1);
    } else {
      return (char) (90 - numEquations + 1);
    }
  }

  private static void fileInput(int numEquations, double[][] coefficients, double[] rightHandSide) {
    try {
      System.out.print("Enter the name of the file: ");
      File file = new File(kb.nextLine());
      String line;
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      for (int i = 0; i < numEquations; i++) {
        line = br.readLine();
        String[] input = line.split(" ");
        for (int j = 0; j < input.length; j++) {
          if (j < input.length - 1) {
            coefficients[i][j] = Double.parseDouble(input[j]);
          }
          if (j == input.length - 1) {
            rightHandSide[i] = Double.parseDouble(input[j]);
          }
        }
      }
      br.close();
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}