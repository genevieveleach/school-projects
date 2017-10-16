import java.util.*;

public class IVoteService implements IVote {

  private Question question;
  private String[] students;

  //TODO: add classroom setting
  //TODO: add results count
  //TODO: add correct results count
  //TODO: answer submission service
  //TODO: decide whether question creation for the teacher goes here or simulation driver

  // Clears question and results maps; does not clear students.
  public void clear() {
    this.question = null;
  }

  public void setQuestion (Question question) {
    this.question = question;
  }

  public String getQuestion() {
    if (questionSetCheck()){
      return "No question set.";
    }
    return question.getPrompt();
  }

  public boolean questionSetCheck() {
    if (question == null) {
      return true;
    }
    return false;
  }

  public String getCorrectResults() {
    String results = "Number of correct answers: ";
    if(question.getQuestionType() == 0) {
      question.getCorrectAnswer();
    } else {
      question.getCorrectAnswers();
    }
    return results;
  }

  public Map<Character, Integer> getAllResults() {
    Map<Character, Integer> results = new HashMap<Character, Integer>();

    return results;
  }

  public void setAnswer (Student student, String ans) {
    List<Character> answer = new ArrayList<Character>();
    StringTokenizer st = new StringTokenizer(ans, ",. ");
    // if it's a single choice question vs a multiple choice question
    if (question.getQuestionType() == 0) {
      char a = st.nextToken().charAt(0);
      boolean legal = checkLegalAnswer(a, question.getPossibleAnswers());
      // If the letter is part of the question set, then add it to the answer list.
      // Else, ignore it.
      if (legal) {
        answer.add(a);
      } else {
        return;
      }
    } else {
      while (st.hasMoreTokens()) {
        char a = st.nextToken().charAt(0);
        boolean legal = checkLegalAnswer(a, question.getPossibleAnswers());
        if (legal) {
          answer.add(a);
        } else {
          continue;
        }
      }
    }
    student.setAnswer((ArrayList<Character>)answer);
  }


  public boolean checkLegalAnswer(char a, List<String> possibleAnswers) {
    boolean legal = false;
    for(String check : possibleAnswers) {
      if (check.charAt(0) == a) {
        legal = true;
        return legal;
      }
    }
    return legal;
  }

}
