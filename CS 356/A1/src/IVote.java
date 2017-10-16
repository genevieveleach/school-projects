import java.util.List;
import java.util.Map;

interface IVote {

  void clear();
  void setQuestion (Question question);
  String getQuestion();
  boolean questionSetCheck();
  String getCorrectResults();
  Map<Character, Integer> getAllResults();
  void setAnswer (Student student, String ans);
  boolean checkLegalAnswer(char a, List<String> possibleAnswers);

}
