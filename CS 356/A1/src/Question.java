import java.util.List;

public class Question {

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

  public int getQuestionType() {
    return questionType;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPossibleAnswers(List<String> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }
  public List<String> getPossibleAnswers() {
    return possibleAnswers;
  }

}
