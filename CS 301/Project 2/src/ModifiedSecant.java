import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ModifiedSecant extends Functions {

  static void evaluate(double x, int nMax, double errorThreshold, double delta, int functionNum) {
    System.out.println("Modified Secant method for part " + functionNum);
    try {
      writeHeaderToFile(functionNum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    double fx;
    double deltax;
    double fxplusdeltax;
    double xnplus1;
    double error;
    double lastError;
    int divergentCount = 0;

    lastError = Double.MAX_VALUE;
    for (int i = 0; i < nMax; i++) {
      fx = f(x, functionNum);
      deltax = delta * x;
      fxplusdeltax = f((x + deltax), functionNum);

      error = fx * (deltax / (fxplusdeltax - fx));

      // if current is > than last 3 times in a row, solution is divergent for points chosen
      if(Math.abs(error) > Math.abs(lastError)) {
        divergentCount++;
      } else if(divergentCount >= 3) {
        System.out.println("Solution is divergent...\n");
        return;
      } else {
        divergentCount = 0;
      }

      if(Math.abs(error) < errorThreshold) {
        System.out.println("Converges at " + x + ".\n");
        return;
      }

      xnplus1 = x - error;

      try {
        writeDataToFile(i, x, xnplus1, error);
      } catch (IOException e) {
        e.printStackTrace();
      }

      x = xnplus1;
      lastError = error;
    }
    System.out.println("Does not converge after " + nMax + " iterations...\n");

  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/ModifiedSecantResults.csv", true)));
    out.println("n,xn,xn+1,Error,Function: " + functionNum);
    out.close();
  }

  private static void writeDataToFile(int n, double xn, double xnplus1, double error) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output/ModifiedSecantResults.csv", true)));
    out.println(n + "," + xn + "," + xnplus1 + "," + error);
    out.close();
  }
}
