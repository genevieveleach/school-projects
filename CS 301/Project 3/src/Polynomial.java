import java.util.ArrayList;

class Polynomial {
  ArrayList<Double> expand(double c, ArrayList<Double> t, int length) {
    ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    // coefficient array
    ArrayList<Double> co = new ArrayList<>();
    for (int i = 0; i < t.size() + 1; ++i) {
      co.add(0.0);
    }
    co.add(0, c);
    for (Double aT : t) {
      matrix.add(shiftRight(co));
      matrix.add(multiply(co, -aT));
      co = addLikeTerms(matrix);
      matrix.clear();
    }
    int n = co.size();
    //padding
    for (int i = 0; i < length - n; ++i) {
      co.add(0.0);
    }
    return co;
  }

  private ArrayList<Double> shiftRight(ArrayList<Double> a) {
    ArrayList<Double> t = new ArrayList<>();
    t.add(0.0);
    for (int i = 0; i < a.size() - 1; i++) {
      t.add(a.get(i));
    }
    return t;
  }

  private ArrayList<Double> multiply(ArrayList<Double> a, double x) {
    ArrayList<Double> t = new ArrayList<>();
    for (double d : a) {
      t.add(d * x);
    }
    return t;
  }

  ArrayList<Double> addLikeTerms(ArrayList<ArrayList<Double>> matrix) {
    ArrayList<Double> combined = new ArrayList<>();
    for (int i = 0; i < matrix.get(0).size(); i++) {
      double sum = 0.0;
      for (ArrayList<Double> aMatrix : matrix) {
        sum += aMatrix.get(i);
      }
      combined.add(sum);
    }
    return combined;
  }
}
