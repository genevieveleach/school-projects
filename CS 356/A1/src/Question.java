import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Question {

  abstract void setCorrectAnswer(char correctAnswer);
  abstract void setCorrectAnswers(String correctAnswers) throws Exception;
  abstract char getCorrectAnswer();
  abstract List<Character> getCorrectAnswers();

  private int questionType;
  private String prompt;
  private List<String> possibleAnswers;

  // if questionType = 0, single choice, else if questionType = 1, multiple choice
  public Question(int questionType) {
    this.questionType = questionType;
    possibleAnswers = new ArrayList<String>();
  }

  public Question(int questionType, String prompt) {
    this.questionType = questionType;
    this.prompt = prompt;
    possibleAnswers = new ArrayList<String>();
  }

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
      String token = st.nextToken().trim();
      this.possibleAnswers.add(token);
    }
  }

  public void setPossibleAnswers(List<String> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }

  public List<String> getPossibleAnswers() {
    return possibleAnswers;
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
}
