public interface IndividualPanel {

  boolean alreadyFollowingUser(User user);

  boolean followingSelf(User user);

  void follow(IndividualUser user);

  void tweet(String message);
}
