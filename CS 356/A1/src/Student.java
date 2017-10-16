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

  public void setAnswer (ArrayList<Character> answer) {
    // will override any previously submitted answers
    answer.clear();
    this.answer = answer;
  }

  public void clearAnswer() {
    answer.clear();
  }

  public ArrayList<Character> getAnswer() {
    return (ArrayList<Character>) answer;
  }


}
