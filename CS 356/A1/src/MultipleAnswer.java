import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//In multiple answer questions, a student can choose any combination of answer choices.
public class MultipleAnswer extends Question {

  private List<Character> correctAnswers = new ArrayList<Character>();

  public MultipleAnswer() {
    super(1);
  }

  public MultipleAnswer(String prompt) {
    super(1, prompt);
  }

  public void setCorrectAnswers(String correctAnswers) {
    StringTokenizer st = new StringTokenizer(correctAnswers, ",. ");
    while (st.hasMoreTokens()) {
      char a = st.nextToken().charAt(0);
      boolean legal = checkLegalAnswer(a);
      if (legal) {
        this.correctAnswers.add(a);
      } else {
        continue;
      }
    }
  }

  public List<Character> getCorrectAnswers() {
    return correctAnswers;
  }

  public boolean checkLegalAnswer(char a) {
    boolean legal = false;
    for(String check : this.getPossibleAnswers()) {
      if (check.charAt(0) == a) {
        legal = true;
        return legal;
      }
    }
    return legal;
  }

  public void setCorrectAnswer(char correctAnswer) {
    this.correctAnswers.add(correctAnswer);
  }

  // Only use this if there is one correct answer in the multiple answer set.
  public char getCorrectAnswer() {
    return correctAnswers.get(0);
  }
}
