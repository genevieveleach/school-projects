public class GroupUser extends User {

  private String groupID;
  private long creationTime;
  private long updateTime;

  public GroupUser(String id, long creationTime) {
    setID(id);
    setCreationTime(creationTime);
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
  public boolean validateID(String id) {
    if(id.contains(" ")) {
      System.out.println("User " + id + " contains spaces!");
      return false;
    }
    return true;
  }

  @Override
  public void setCreationTime(long creationTime) {
    this.creationTime = creationTime;
  }

  @Override
  public long getCreationTime() {
    return creationTime;
  }

  @Override
  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public long getUpdateTime() {
    return updateTime;
  }

  @Override
  public String toString() {
    return this.getID();
  }
}
