public class TotalGroups implements Visitor {

  private int count = 0;

  @Override
  public void visit(User node) {
    if(node instanceof GroupUser) {
      count++;
    } else {
      return;
    }
  }

  public int result() {
    return count;
  }
}
