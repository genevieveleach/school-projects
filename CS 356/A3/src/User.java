import javax.swing.tree.DefaultMutableTreeNode;

//composite
public abstract class User extends DefaultMutableTreeNode {

  public abstract void setID(String id);

  public abstract String getID();

  public abstract boolean validateID(String id);

  public abstract void setCreationTime(long creationTime);

  public abstract long getCreationTime();

  public abstract void setLastUpdateTime(long lastUpdateTime);

  public abstract long getLastUpdateTime();

  public abstract String toString();
}
