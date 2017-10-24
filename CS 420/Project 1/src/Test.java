public class Test {
  public static void main(String[] args) throws Exception {
    int[] i = {1,2,4,0,5,6,8,3,7};
    Board b = new Board(i, 1);
    System.out.println(b.isValid());
  }
}
