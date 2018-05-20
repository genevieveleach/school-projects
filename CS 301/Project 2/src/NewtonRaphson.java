import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class NewtonRaphson extends Functions {

  //TODO: clever checks in your program to be warned and stop if you have a divergent solution
  static void evaluate(double x, int nMax, double errorThreshold, double delta, int functionNum) {
    System.out.println("Newton-Raphson method for part " + functionNum);
    try {
      writeHeaderToFile(functionNum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    double fx;
    double fprimex;
    double error;

    fx = f(x, functionNum);
    fprimex = fPrime(x, functionNum);

    try {
      writeDataToFile(0, x, fx, fprimex, Double.MAX_VALUE);
    } catch (IOException e) {
      e.printStackTrace();
    }

    for(int i = 1; i < nMax; i++) {
      fprimex = fPrime(x, functionNum);

      if(Math.abs(fprimex) < delta) {
        System.out.println("Small derivative...");
        return;
      }

      error = fx/fprimex;
      x = x - error;
      fx = f(x, functionNum);

      try {
        writeDataToFile(i, x, fx, fprimex, error);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(Math.abs(error) <= errorThreshold) {
        System.out.println("Converges at " + x + ".\n");
        return;
      }
    }

    System.out.println("Does not converge after " + nMax + "iterations...\n");
  }

  private static void writeDataToFile(int n, double x, double fx, double fprimex, double error) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../output/NewtonResults.csv")),true);
    out.println(n + "," + x + "," + fx + "," + fprimex + "," + error);
    out.close();
  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../output/FalsePositionResults.csv")));
    out.println("n,x,f(x),f'(x),ε,Function: " + functionNum);
    out.close();
  }
}
