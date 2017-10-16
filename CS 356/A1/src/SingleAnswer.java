import java.util.ArrayList;
import java.util.List;

// In single answer questions, a student can choose only one answer choice.
public class SingleAnswer extends Question {

  private char correctAnswer;

  public SingleAnswer() {
    super(0);
  }

  SingleAnswer(String prompt) {
    super(0, prompt);
  }

  public void setCorrectAnswer(char correctAnswer) {
    boolean legal = checkLegalAnswer(correctAnswer);
    if (legal) {
      this.correctAnswer = correctAnswer;
    } else {
      System.out.println("No legal answer choices provided. Question will not have a correct answer.");
    }
    this.correctAnswer = correctAnswer;
  }

  char getCorrectAnswer() {
    return correctAnswer;
  }

  void setCorrectAnswers(String correctAnswers) throws Exception {
    char a = correctAnswers.trim().charAt(0);
    boolean legal = checkLegalAnswer(a);
    if (legal) {
      this.correctAnswer = a;
    } else {
      System.out.println("No legal answer choices provided. Question will not have a correct answer.");
    }
  }

  List<Character> getCorrectAnswers() {
    List<Character> list = new ArrayList<>();
    list.add(getCorrectAnswer());
    return list;
  }
}
