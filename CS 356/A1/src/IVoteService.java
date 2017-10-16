import java.util.*;

public class IVoteService implements IVote {

  private Question question;
  private List<Student> students;

  IVoteService() {
    students = new ArrayList<>();
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public List<Student> getStudents() {
    return students;
  }

  // Clears question and student answers; does not clear students.
  public void clear() {
    this.question = null;
    for (Student student : students) {
      student.clearAnswer();
    }
  }

  public void setQuestion (Question question) {
    this.question = question;
  }

  public String getQuestion() {
    if (questionNotSetCheck()){
      return "No question set.";
    }
    return question.getPrompt();
  }

  public boolean questionNotSetCheck() {
    return question == null;
  }

  public String getCorrectResults() {
    String results = "";
    if (questionNotSetCheck()) {
      return "Question not set; could not return number of correct results.";
    }

    if (question.getQuestionType() == 0) {
      char correctAnswer = question.getCorrectAnswer();
      int count = 0;
      for (Student student : students) {
        ArrayList<Character> list = student.getAnswer();
        for (Character ans : list) {
          char a = ans;
          if (a == correctAnswer) {
            count++;
          }
        }
      }
      return (results + "{" + correctAnswer + "=" + count + "}");
    } else {
      List<Character> correctAnswers = question.getCorrectAnswers();
      Map<Character, Integer> correctAnswerDistribution = new HashMap<>();
      for (Student student : students) {
        ArrayList<Character> list = student.getAnswer();
        for (char c : list) {
          if (correctAnswers.contains(c)) {
            if (correctAnswerDistribution.get(c) != null) {
              int count = correctAnswerDistribution.get(c);
              count++;
              correctAnswerDistribution.put(c, count);
            } else {
              correctAnswerDistribution.put(c, 1);
            }
          }
        }
      }
      return results + correctAnswerDistribution.toString();
    }
  }

  public String getAllResults() {
    if(questionNotSetCheck()) {
      return "Question not set; could not return number of results.";
    }
    Map<Character, Integer> results = new HashMap<>();
    for (Student student : students) {
      ArrayList<Character> answers = student.getAnswer();
      for(char answer : answers) {
        if(results.get(answer) != null) {
          int count = results.get(answer) + 1;
          results.put(answer, count);
        } else {
          results.put(answer, 1);
        }
      }
    }
    return results.toString();
  }

  public void setAnswer (Student student, String ans) {
    List<Character> answer = new ArrayList<>();
    StringTokenizer st = new StringTokenizer(ans, ",. ;");
    // if it's a single choice question vs a multiple choice question
    if (question.getQuestionType() == 0) {
      char a = st.nextToken().trim().charAt(0);
      boolean legal = checkLegalAnswer(a, question.getPossibleAnswers());
      // If the letter is part of the question set, then add it to the answer list.
      // Else, ignore it.
      if (legal) {
        if (!answer.contains(a)) {
          answer.add(a);
        }
      } else {
        return;
      }
    } else {
      while (st.hasMoreTokens()) {
        char a = st.nextToken().trim().charAt(0);
        boolean legal = checkLegalAnswer(a, question.getPossibleAnswers());
        if (legal) {
          if(!answer.contains(a)) {
            answer.add(a);
          }
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
