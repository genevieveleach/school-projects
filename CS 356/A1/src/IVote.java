import java.util.List;

interface IVote {
  void clear();
  void setQuestion (Question question);
  String getQuestion();
  boolean questionNotSetCheck();
  String getCorrectResults();
  String getAllResults();
  void setAnswer (Student student, String ans);
  boolean checkLegalAnswer(char a, List<String> possibleAnswers);
  void addStudent(Student student);
  List<Student> getStudents();
}
