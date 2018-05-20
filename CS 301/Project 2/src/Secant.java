import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Secant {

  static void evaluate(double a, double b, int nMax, double errorThreshold, int functionNum) {
    System.out.println("Secant method for part " + functionNum);
    try {
      writeHeaderToFile(functionNum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    double fa;
    double fb;
    double error;


  }

  private static void writeHeaderToFile(int functionNum) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../output/SecantResults.csv")));
    out.println("n,Function: " + functionNum);
    out.close();
  }
}
