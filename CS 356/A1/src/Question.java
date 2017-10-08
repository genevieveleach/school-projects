public class Question {

  private int QuestionType;

  // if QuestionType = 0, single choice, else if QuestionType = 1, multiple choice
  public Question(int QuestionType) {
    this.QuestionType = QuestionType;
  }

  public int getQuestionType() {
    return QuestionType;
  }
}
