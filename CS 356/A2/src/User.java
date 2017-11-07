import javax.swing.tree.DefaultMutableTreeNode;

//composite
public abstract class User extends DefaultMutableTreeNode {

  public abstract void setID(String id);

  public abstract String getID();

  public abstract String toString();
}
