import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

  public static void main(String[] args) {
    //read input
    System.out.print("Please provide input file name: ");
    Scanner kb = new Scanner(System.in);
    String fileName = kb.nextLine();
    ArrayList<String> lines = readFile(fileName);
    assert lines != null;

    //separate x and f[]
    String[] s = lines.get(0).split(" ");
    double[] x = new double[s.length];
    for (int i = 0; i < s.length; i++) {
      x[i] = Double.parseDouble(s[i]);
    }

    s = lines.get(1).split(" ");
    double[] y = new double[s.length];
    for (int i = 0; i < s.length; i++) {
      y[i] = Double.parseDouble(s[i]);
    }

    System.out.print("Please provide \"fraction\" or \"decimal\" mode for LaGrange\nNote: fractions may not be fully reduced: ");
    String mode = kb.nextLine().toLowerCase();

    //solve
    DividedDifference dd = new DividedDifference(x,y);
    dd.solve();
    System.out.println("Divided Difference Table: ");
    dd.printDDTable();
    System.out.println("Newton's Interpolating Polynomial: ");
    dd.printPolynomial();
    LaGrange lg = new LaGrange(mode);
    System.out.println("LaGrange's Form: ");
    System.out.println(lg.solve(x,y) + "\n");
    // simplified should be the same for both lagrange and divided difference
    System.out.println("Simplified Polynomial: ");
    dd.printSimplified();
  }

  private static ArrayList<String> readFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      ArrayList<String> list = new ArrayList<>();
      list.add(br.readLine());
      list.add(br.readLine());
      return list;
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return null;
  }
}
