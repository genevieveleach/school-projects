import java.util.List;

//In multiple answer questions, a student can choose any combination of answer choices.
public class MultipleAnswer extends Question {

  private List<Character> correctAnswers;

  public MultipleAnswer() {
    super(1);
  }

  public MultipleAnswer(String prompt) {
    super(1, prompt);
  }

  public List<Character> getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers() {
    this.correctAnswers = correctAnswers;
  }
}
