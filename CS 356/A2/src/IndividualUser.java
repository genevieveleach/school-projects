import javax.swing.DefaultListModel;

public class IndividualUser extends User implements Observer, Subject {

  private String id;
  private DefaultListModel<Observer> followers = new DefaultListModel<>();
  private DefaultListModel<Subject> following = new DefaultListModel<>();
  private DefaultListModel<String> newsFeed;
  private String message;
  private boolean changeState = false;

  public IndividualUser(String id) {
    setID(id);
    this.allowsChildren = false;
    this.newsFeed = new DefaultListModel<>();
  }

  public void tweet(String message) {
    this.message = message;
    newsFeed.addElement("[" + this.getID() + "]: " + message);
    this.changeState = true;
    notifyObservers();
  }

  public Object[] getMessages() {
    return this.newsFeed.toArray();
  }

  public Object[] getFollowers() {
    return this.followers.toArray();
  }

  public DefaultListModel<String> getNewsFeedListModel() {
    return newsFeed;
  }

  public Object[] getFollowing() {
    return this.following.toArray();
  }

  public DefaultListModel<Subject> getFollowingListModel() {
    return following;
  }

  public void follow(IndividualUser user) {
    setSubject(user);
    user.register(this);
  }

  @Override
  public void update(Subject s) {
    String update = s.getUpdate(this);
    this.newsFeed.addElement("[" + s.toString() + "]: " + update);
  }

  @Override
  public void setSubject(Subject s) {
    following.addElement(s);
  }

  @Override
  public void register(Observer o) {
    followers.addElement(o);
  }

  @Override
  public void unregister(Observer o) {
    followers.removeElement(o);
  }

  @Override
  public void notifyObservers() {
    if(changeState) {
      for(Object user : followers.toArray()) {
        ((Observer) user).update(this);
      }
      changeState = false;
    }
    else {
      return;
    }
  }

  @Override
  public String getUpdate(Observer o) {
    return this.message;
  }

  @Override
  public void setID(String id) {
    this.id = id;
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public String toString() {
    return this.getID();
  }
}
