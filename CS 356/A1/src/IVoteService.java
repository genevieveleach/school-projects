import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IVoteService {

  //TODO: add classroom setting
  //TODO: add results count
  //TODO: add correct results count
  //TODO: answer submission service
  //TODO: decide whether question creation for the teacher goes here or simulation driver

  public void setAnswer (Question question, Student student, String ans) {
    List<Character> answer = new ArrayList<Character>();
    StringTokenizer st = new StringTokenizer(ans, ",. ");
    if (question.getQuestionType() == 0) {
      char a = st.nextToken().charAt(0);
      boolean legal = checkLegalAnswer(a, question.getPossibleAnswers());
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
