import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Bisection extends Functions {

  static void evaluate(double a, double b, int nMax, double errorThreshold, int functionNum) {
    System.out.println("Bisection method for part " + functionNum);
    try {
      writeHeaderToFile(functionNum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    double error = 0;
    double c;
    double fa;
    double fb;
    double fc;
    double lastError;
    int divergentCount = 0;

    fa = f(a, functionNum);
    fb = f(b, functionNum);

    if (Math.signum(fa) == Math.signum(fb)) {
      System.out.println("f(a) and f(b) have same sign, no root...\n");
      return;
    }
    lastError = Double.MAX_VALUE;
    error = b-a;
    for (int i = 0; i < nMax; i++) {
      error = error/2;
      c = a + error;
      fc = f(c, functionNum);

      // if current is > than last 3 times in a row, solution is divergent for points chosen
      if(Math.abs(error) > Math.abs(lastError)) {
        divergentCount++;
      } else if(divergentCount >= 3) {
        System.out.println("Solution is divergent...\n");
        return;
      } else {
        divergentCount = 0;
      }

      try {
        writeDataToFile(i, a, b, c, fa, fb, fc, error);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(Math.abs(error) <= errorThreshold) {
        System.out.println("Converges at " + c + ".\n");
        return;
      }

      if(Math.signum(fa) != Math.signum(fc)) {
        b = c;
        fb = fc;
      } else {
        a = c;
        fa = fc;
      }
      lastError = error;
    }

    System.out.println("Does not converge after " + nMax + "iterations...\n");
  }

  private static void writeDataToFile(int n, double a, double b, double c, double fa, double fb, double fc, double error) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/BisectionResults.csv", true)));
    out.println(n + "," + a + "," + b + "," + c + "," + fa + "," + fb + "," + fc + "," + error);
    out.close();
  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/BisectionResults.csv", true)));
    out.println("n,a,b,c,f(a),f(b),f(c),Error,Function: " + functionNum);
    out.close();
  }
}
