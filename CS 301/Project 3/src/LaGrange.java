import java.util.ArrayList;

class LaGrange {

  private int mode;

  LaGrange(String mode) {
    switch (mode) {
      case "fraction":
        this.mode = 0;
        break;
      case "decimal":
        this.mode = 1;
        break;
      default:
        //assume mode to fraction if incorrect input
        this.mode = 0;
    }
  }

  String solve(double[] x, double[] y) {
    ArrayList<String> xMultipliers = new ArrayList<>();
    ArrayList<Fraction> fxiByLiDenominator = new ArrayList<>();
    ArrayList<Double> coefficients = new ArrayList<>();
    for (int i = 0; i < x.length; i++) {
      StringBuilder xMulti = new StringBuilder();
      double denominator = 1;
      for (int j = 0; j < x.length; j++) {
        if (j != i) {
          xMulti.append("(");
          String sign = "";
          if (x[j] > 0) {
            sign = "-";
          } else if (x[j] < 0) {
            sign = "+";
          }
          if (x[j] == 0) {
            xMulti.append("x)");
          } else {
            xMulti.append(String.format("x%s%.5f)", sign, Math.abs(x[j])));
          }
        }
        if (j != i) {
          denominator *= (x[i] - x[j]);
        }
      }
      xMultipliers.add(xMulti.toString());
      coefficients.add(y[i]/denominator);
      fxiByLiDenominator.add(new Fraction(y[i], denominator));
    }

    StringBuilder polynomial = new StringBuilder();
    Fraction fraction;
    for (int i = 0; i < x.length; i++) {
      if (mode == 0) {
        //fraction mode
        fraction = fxiByLiDenominator.get(i);
        if (fraction.getNumerator() >= 0 && i != 0) {
          polynomial.append("+");
        }
        if(fraction.getNumerator() == 1 && fraction.getDenominator() == 1) {
          polynomial.append(xMultipliers.get(i));
        } else if (fraction.getNumerator() == -1 && fraction.getDenominator() == 1) {
          polynomial.append(String.format("-%s ", xMultipliers.get(i)));
        } else if (fraction.getDenominator() == 1) {
          polynomial.append(String.format("%.5f%s ", fraction.getNumerator(), xMultipliers.get(i)));
        } else if (fraction.getNumerator() == 1) {
          polynomial.append(String.format("%s/%.5f ", xMultipliers.get(i), fraction.getDenominator()));
        } else if (fraction.getNumerator() == -1) {
          polynomial.append(String.format("-%s/%.5f ", xMultipliers.get(i), fraction.getDenominator()));
        } else {
          polynomial.append(String.format("%.5f%s/%.5f ", fraction.getNumerator(), xMultipliers.get(i), fraction.getDenominator()));
        }
      } else {
        //decimal mode
        double coeff = coefficients.get(i);
        if (coeff >= 0 && i != 0) {
          polynomial.append("+");
        }
        polynomial.append(String.format("%.5f%s ", coeff, xMultipliers.get(i)));
      }
    }
    return polynomial.toString();
  }
}

class Fraction {
  private double numerator;
  private double denominator;

  public Fraction(int num) {
    numerator = num;
    denominator = 1;
  }

  //if denominator is negative, put it into numerator (and cancel if numerator is already negative)
  Fraction(double num, double denom) {
    numerator = (denom < 0 ? -num : num);
    if (denom == 0) {
      denominator = 1;
    }
    denominator = (denom < 0 ? -denom : denom);
    reduce();
  }

  public void setNumerator(int num) {
    numerator = num;
    reduce();
  }

  double getNumerator() {
    return numerator;
  }

  public void setDenominator(int denom) {
    if (denom > 0) {
      denominator = denom;
      reduce();
    }
    else if (denom < 0) {
      numerator = -numerator;
      denominator = -denom;
      reduce();
    }
  }

  double getDenominator() {
    return denominator;
  }

  //does not work as intended because of doubles rather than ints
  private void reduce() {
    // find the larger of the numerator and denominator
    double n = numerator, d = denominator, largest;
    if (numerator < 0) {
      n = -numerator;
    }
    if (n > d) {
      largest = n;
    }
    else {
      largest = d;
    }

    // find the largest number that divide the numerator and denominator evenly
    double gcd = 0;
    for (double i = largest; i >= 2; i--) {
      if (numerator % i == 0 && denominator % i == 0) {
        gcd = i;
        break;
      }
    }

    // divide the largest common denominator out of numerator, denominator
    if (gcd != 0) {
      numerator /= gcd;
      denominator /= gcd;
    }
  }
}