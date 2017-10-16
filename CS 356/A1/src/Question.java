import java.util.List;
import java.util.StringTokenizer;

public abstract class Question {

  private int questionType;
  private String prompt;
  private List<String> possibleAnswers;

  // if questionType = 0, single choice, else if questionType = 1, multiple choice
  public Question(int questionType) {
    this.questionType = questionType;
  }

  public Question(int questionType, String prompt) {
    this.questionType = questionType;
    this.prompt = prompt;
  }
  abstract void setCorrectAnswer(char correctAnswer);
  abstract void setCorrectAnswers(String correctAnswers);
  abstract char getCorrectAnswer();
  abstract List<Character> getCorrectAnswers();

  public int getQuestionType() {
    return questionType;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getPrompt() {
    return prompt;
  }

  // sets answer choices. choices can be strings, separated by ';'
  // they will be labeled by the first character of each string.
  public void setPossibleAnswers(String possibleAnswers) {
    StringTokenizer st = new StringTokenizer(possibleAnswers, ";");
    while (st.hasMoreTokens()) {
      this.possibleAnswers.add(st.nextToken());
    }
  }

  public void setPossibleAnswers(List<String> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }
  public List<String> getPossibleAnswers() {
    return possibleAnswers;
  }

}
