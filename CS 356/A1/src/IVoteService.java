import java.util.*;

public class IVoteService implements IVote {

  private Question question;
  private List<Student> students;

  public void addStudent(Student student) {
    students.add(student);
  }

  // Clears question; does not clear students.
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
    if (question == null) {
      return true;
    }
    return false;
  }

  public String getCorrectResults() {
    String results = "Number of correct answers: ";

    if (questionNotSetCheck()) {
      return "Question not set; could not return number of correct results.";
    }

    if (question.getQuestionType() == 0) {
      char correctAnswer = question.getCorrectAnswer();
      int count = 0;
      for (Student student : students) {
        ArrayList<Character> list = student.getAnswer();
        for (Character aList : list) {
          char a = (char) aList;
          if (a == correctAnswer) {
            count++;
          }
        }
      }
      return (results + "{" + correctAnswer + "=" + count + "}");
    } else {
      List<Character> correctAnswers = question.getCorrectAnswers();
      Map<Character, Integer> correctAnswerDistribution = new HashMap<Character, Integer>();
      for (Student student : students) {
        ArrayList<Character> list = student.getAnswer();
        for (Character ans : list) {
          if (correctAnswers.contains(ans)) {
            if (correctAnswerDistribution.containsValue(ans)) {
              correctAnswerDistribution.put(ans, (correctAnswerDistribution.get(ans) + 1));
            } else {
              correctAnswerDistribution.put(ans, 1);
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

    Map<Character, Integer> results = new HashMap<Character, Integer>();
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
