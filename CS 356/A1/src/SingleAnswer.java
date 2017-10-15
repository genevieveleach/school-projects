// In single answer questions, a student can choose only one answer choice.
public class SingleAnswer extends Question {

  private char correctAnswer;

  public SingleAnswer() {
    super(0);
  }

  public SingleAnswer(String prompt) {
    super(1, prompt);
  }

  public char getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer() {
    this.correctAnswer = correctAnswer;
  }

}
