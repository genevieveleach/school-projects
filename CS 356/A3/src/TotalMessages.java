public class TotalMessages implements Visitor {

  private int count = 0;

  @Override
  public void visit(User node) {
    if(node instanceof IndividualUser) {
      count += ((IndividualUser)node).getMessages().length;
    }
  }

  public int result() {
    return count;
  }
}
