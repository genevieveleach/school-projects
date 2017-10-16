import java.util.ArrayList;
import java.util.List;

// In single answer questions, a student can choose only one answer choice.
public class SingleAnswer extends Question {

  private char correctAnswer;

  public SingleAnswer() {
    super(0);
  }

  public SingleAnswer(String prompt) {
    super(1, prompt);
  }

  public void setCorrectAnswer(char correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  char getCorrectAnswer() {
    return correctAnswer;
  }

  void setCorrectAnswers(String correctAnswers) {
    this.correctAnswer = correctAnswers.charAt(0);
  }

  List<Character> getCorrectAnswers() {
    List<Character> list = new ArrayList<Character>();
    list.add(getCorrectAnswer());
    return list;
  }
}
