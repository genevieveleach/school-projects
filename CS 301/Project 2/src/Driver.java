import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Driver {

  public static void main(String[] args) {
    final int nMax = 100;
    final double ea = 0.01;
    final double delta = 0.01;

    try {
      clearFiles();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // part a
    Bisection.evaluate(0,1, nMax, ea,1);
    Bisection.evaluate(1,2, nMax, ea, 1);
    Bisection.evaluate(3, 4, nMax, ea, 1);

    FalsePosition.evaluate(0,1, nMax, ea,1);
    FalsePosition.evaluate(1,2, nMax, ea, 1);
    FalsePosition.evaluate(3, 4, nMax, ea, 1);

    NewtonRaphson.evaluate(0, nMax, ea, delta, 1);
    NewtonRaphson.evaluate(2, nMax, ea, delta, 1);
    NewtonRaphson.evaluate(3, nMax, ea, delta, 1);

    Secant.evaluate(0, 1, nMax, ea, 1);
    Secant.evaluate(1, 2, nMax, ea, 1);
    Secant.evaluate(3, 4, nMax, ea, 1);

    ModifiedSecant.evaluate(0.5, nMax, ea, delta, 1);
    ModifiedSecant.evaluate(1.5, nMax, ea, delta, 1);
    ModifiedSecant.evaluate(3, nMax, ea, delta, 1);

    // part b
    Bisection.evaluate(120, 130, nMax, ea, 2);

    FalsePosition.evaluate(120, 130, nMax, ea, 2);

    NewtonRaphson.evaluate(120, nMax, ea, delta, 2);

    Secant.evaluate(120, 130, nMax, ea, 2);

    ModifiedSecant.evaluate(120, nMax, ea, delta, 2);
  }

  private static void clearFiles() throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(new File("output/BisectionResults.csv"));
    writer.close();
    writer = new PrintWriter(new File("output/FalsePositionResults.csv"));
    writer.close();
    writer = new PrintWriter(new File("output/NewtonResults.csv"));
    writer.close();
    writer = new PrintWriter(new File("output/SecantResults.csv"));
    writer.close();
    writer = new PrintWriter(new File("output/ModifiedSecantResults.csv"));
    writer.close();
  }
}
