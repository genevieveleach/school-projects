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
      char a = st.nextToken().trim().charAt(0);
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


  public void setCorrectAnswer(char correctAnswer) {
    boolean legal = checkLegalAnswer(correctAnswer);
    if (legal) {
      this.correctAnswers.add(correctAnswer);
    } else {
      System.out.println("No legal answer choices provided. Question will not have a correct answer.");
    }
  }

  public char getCorrectAnswer() {
    return correctAnswers.get(0);
  }
}
