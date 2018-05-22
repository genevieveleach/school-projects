import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Secant extends Functions {

  static void evaluate(double a, double b, int nMax, double errorThreshold, int functionNum) {
    System.out.println("Secant method for part " + functionNum);
    try {
      writeHeaderToFile(functionNum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    double fa;
    double fb;
    double d;
    double lastD;
    int divergentCount = 0;

    fa = f(a, functionNum);
    fb = f(b, functionNum);

    if(Math.abs(fa) > Math.abs(fb)) {
      double temp = a;
      a = b;
      b = temp;
      temp = fa;
      fa = fb;
      fb = temp;
    }

    try {
      writeDataToFile(0, a, fa);
      writeDataToFile(1, b, fb);
    } catch (IOException e) {
      e.printStackTrace();
    }

    lastD = Double.MAX_VALUE;
    for (int i = 2; i < nMax; i++) {
      if(Math.abs(fa) > Math.abs(fb)) {
        double temp = a;
        a = b;
        b = temp;
        temp = fa;
        fa = fb;
        fb = temp;
      }

      d = (b-a)/(fb-fa);
      b = a;
      fb = fa;
      d *= fa;

      if(Math.abs(d) < errorThreshold) {
        System.out.println("Converges at " + b + ".\n");
        return;
      }

      a -= d;
      fa = f(a, functionNum);

      // if current is > than last 3 times in a row, solution is divergent for points chosen
      if(Math.abs(d) > Math.abs(lastD)) {
        divergentCount++;
      } else if(divergentCount >= 3) {
        System.out.println("Solution is divergent...\n");
        return;
      } else {
        divergentCount = 0;
      }

      try {
        writeDataToFile(i, a, fa, d);
      } catch (IOException e) {
        e.printStackTrace();
      }

      lastD = d;
    }

    System.out.println("Does not converge after " + nMax + "iterations...\n");
  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/SecantResults.csv", true)));
    out.println("n,a,f(a),Error,Function: " + functionNum);
    out.close();
  }

  private static void writeDataToFile(int n, double a, double fa, double error) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/SecantResults.csv", true)));
    out.println(n + "," + a + "," + fa + "," + error);
    out.close();
  }

  private static void writeDataToFile(int n, double a, double fa) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/SecantResults.csv", true)));
    out.println(n + "," + a + "," + fa);
    out.close();
  }
}
