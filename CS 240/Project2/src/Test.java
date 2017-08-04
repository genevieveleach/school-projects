/**
 * Created by Genniy on 5/2/2016.
 */
public class Test {
  public static void main(String[] args) {
    Calculator calc = new Calculator();
    System.out.println("Passing equation: 2400/80-14*2 \nIt should equal: 2.0");
    System.out.println("It calculates to: " + calc.solve("2400/80-14*2"));
  }
}
