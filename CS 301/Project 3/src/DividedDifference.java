import java.util.ArrayList;

class DividedDifference {

  private double[][] table;
  private double[] coefficients;

  DividedDifference(double[] x, double[] y) {
    table = new double[x.length][x.length+1];
    for(int i = 0; i < x.length; i++) {
      table[i][0] = x[i];
      table[i][1] = y[i];
    }
  }

  void solve() {
    int n = table[0].length;
    for(int j = 2; j < n; j++) {
      for (int i = 0; i < n - j; i++) {
        table[i][j] = (table[i+1][j-1] - table[i][j-1]) / (table[i+(j-1)][0] - table[i][0]);
      }
    }

    coefficients = new double[table.length];
    for(int i = 0, j = 1; i < coefficients.length; i++, j++) {
      coefficients[i] = table[0][j];
    }
  }

  void printDDTable() {
    int height = 2 * table.length;
    int width = table[0].length;

    String[][] toPrint = new String[height][width];

    //set up blanks
    for (int i = 0; i < toPrint.length; i++) {
      for (int j = 0; j < toPrint[0].length; j++) {
        toPrint[i][j] = String.format("%8s", " ");
      }
    }

    //insert x
    for (int i = 0, offset = 0; i < table.length; i++, offset += 2) {
      toPrint[offset][0] = String.format("%8.5f", table[i][0]);
    }

    //insert y
    for (int i = 0, offset = 0; i < table.length; i++, offset += 2) {
      toPrint[offset][1] = String.format("%8.5f", table[i][1]);
    }

    //rest of table
    int n = table[0].length;
    for (int column = 2; column < n; column++) {
      int offset = column - 1;
      for (int row = 0; row < n - column; row++, offset += 2) {
        toPrint[offset][column] = String.format("%8.5f", table[row][column]);
      }
    }

    //print
    for (String[] s: toPrint) {
      for (String num : s) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }


  void printPolynomial() {
    ArrayList<String> xTerms = new ArrayList<>();
    String sign = "";
    for (int i = 0; i < table.length - 1; i++) {
      double num = table[i][0];
      if(num < 0) {
        sign = "+";
      } else if (num > 0) {
        sign = "-";
      }
      if(Math.round(num) == 0) {
        xTerms.add("(x)");
      } else {
        xTerms.add(String.format("(x%s%.5f)", sign, Math.abs(num)));
      }
    }

    StringBuilder polynomial = new StringBuilder(String.format("%.5f", coefficients[0]));
    for (int i = 1; i < xTerms.size() + 1; i++) {
      double num = coefficients[i];
      if (num != 0) {
        if (num > 0) {
          sign = "+";
        } else {
          sign = "-";
        }
        StringBuilder xMultipliers = new StringBuilder();
        for (int j = 0; j < i; j++) {
          xMultipliers.append(xTerms.get(j));
        }
        polynomial.append(String.format(" %s%.5f%s", sign, Math.abs(num), xMultipliers.toString()));
      }
    }
    System.out.println(polynomial + "\n");
  }

  void printSimplified() {
    Polynomial p = new Polynomial();
    ArrayList<Double> t = new ArrayList<>();

    ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < table[0].length - 1; i++) {
      t.add(0.0);
    }
    t.add(0, coefficients[0]);
    matrix.add(t);

    for(int i = 1; i < coefficients.length; i++) {
      t = new ArrayList<>();
      double c = coefficients[i];
      for (int j = 0; j < i; j++) {
        t.add(table[j][0]);
      }
      matrix.add(p.expand(c, t, table[0].length));
    }

    t = p.addLikeTerms(matrix);

    System.out.println(simpleString(t));
  }

  private String simpleString(ArrayList<Double> t) {
    StringBuilder polynomial = new StringBuilder();
    String power;
    for (int i = 0; i < t.size(); i++) {
      Double f = t.get(i);
      power = String.format("x^%d", i);
      if (f != 0) {
        if (i == 0) {
          //is c
          polynomial.append(String.format("%+.5f", f));
        } else {
          //x^power
          polynomial.append(String.format(" %+.5f%s", f, power));
        }
      }
    }
    return polynomial.toString();
  }

  private void debugPrintTable() {
    for (double[] aTable : this.table) {
      for (double anATable : aTable) {
        System.out.printf("%8.5f ", anATable);
      }
      System.out.println("\n");
    }
  }
}
