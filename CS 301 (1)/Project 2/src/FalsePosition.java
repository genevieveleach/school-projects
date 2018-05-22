import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class FalsePosition extends Functions {

  //TODO: clever checks in your program to be warned and stop if you have a divergent solution
  static void evaluate(double a, double b, int nMax, double errorThreshold, int functionNum) {
    System.out.println("False-Position method for part " + functionNum);
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

    fa = f(a, functionNum);
    fb = f(b, functionNum);

    if (Math.signum(fa) == Math.signum(fb)) {
      System.out.println("f(a) and f(b) have same sign, no root...");
      return;
    }

    double lastC = 0;
    for (int i = 0; i < nMax; i++) {
      c = (a*fb - b*fa)/(fb - fa);
      error = (c-lastC)/c;

      fc = f(c, functionNum);

      try {
        writeDataToFile(i, a, b, c, fa, fb, fc, error);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(Math.abs(error) <= errorThreshold) {
        System.out.println("Converges at " + c + ".\n");
        return;
      }

      lastC = c;
      if (Math.signum(fa) != Math.signum(fc)) {
        b = c;
        fb = fc;
      } else {
        a = c;
        fa = fc;
      }
    }

    System.out.println("Does not converge after " + nMax + "iterations...\n");
  }

  private static void writeDataToFile(int n, double a, double b, double c, double fa, double fb, double fc, double error) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/FalsePositionResults.csv", true)));
    out.println(n + "," + a + "," + b + "," + c + "," + fa + "," + fb + "," + fc + "," + error);
    out.close();
  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/FalsePositionResults.csv", true)));
    out.println("n,a,b,c,f(a),f(b),f(c),Error,Function: " + functionNum);
    out.close();
  }
}