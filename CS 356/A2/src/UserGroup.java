public class UserGroup extends User {

  private String groupID;

  public UserGroup(String id) {
    setID(id);
  }

  public void insert(User user, int childIndex) {
    super.insert(user, childIndex);
  }

  @Override
  public void setID(String id) {

  }

  @Override
  public String getID() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }
}
