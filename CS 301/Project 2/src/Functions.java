class Functions {

  static double f(double x, int functionNum) {
    if(functionNum == 1) {
      return 2*Math.pow(x,3) - 11.7*Math.pow(x,2) + 17.7*x - 5;
    } else {
      return x + 10 - x*Math.cosh(50/x);
    }
  }

  static double fPrime(double x, int functionNum) {
    if(functionNum == 1) {
      return 6*Math.pow(x,2) - 23.4*x + 17.7;
    } else {
      return (50*Math.sinh(50/x)/x) - Math.cosh(50/x) + 1;
    }
  }

}
