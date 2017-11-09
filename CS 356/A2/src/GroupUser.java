public class GroupUser extends User {

  private String groupID;

  public GroupUser(String id) {
    setID(id);
  }

  public void insert(User user, int childIndex) {
    super.insert(user, childIndex);
  }

  @Override
  public void setID(String id) {
    this.groupID = id;
  }

  @Override
  public String getID() {
    return this.groupID;
  }

  @Override
  public String toString() {
    return this.getID();
  }
}
