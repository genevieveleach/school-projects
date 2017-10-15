import java.util.ArrayList;
import java.util.List;

public class Student {

  private String ID;
  private List<Character> answer;

  public Student(String ID) {
    this.ID = ID;
    answer = new ArrayList<Character>();
  }

  public String getID() {
    return ID;
  }

  public ArrayList<Character> getAnswer() {
    return (ArrayList<Character>) answer;
  }

}
