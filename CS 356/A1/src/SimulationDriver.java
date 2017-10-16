import java.io.*;
import java.util.*;

public class SimulationDriver {

  private static final boolean FILE_READ = true;
  private static IVoteService iVote;

  public static void main(String[] args) throws IOException {
    iVote = new IVoteService();
    if(FILE_READ) {
      fileDriver();
    } else {
      kbDriver();
    }
  }

  private static void kbDriver() {
    Scanner kb = new Scanner(System.in);
    Random rand = new Random();
    try {
      FileWriter fw = new FileWriter(new File("results.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //creates a random amount of students for the classroom and their IDs
    int numStudents = rand.nextInt(30);
    for(int i = 0; i < numStudents; i++) {
      String studentID = createRandomID();
      Student student = new Student(studentID);
      iVote.addStudent(student);
    }

    System.out.println("Created " + numStudents + " students.");

    System.out.print("How many questions do you want to enter?: ");
    int quizLength = kb.nextInt();
    //clear end of line character
    kb.nextLine();

    for(int i = 0; i < quizLength; i++) {
      System.out.println("Question " + (i+1) + ":");

      // Set question type
      System.out.print("Is this question single answer (please type 0) or multiple answer (please type 1)?: ");
      int questionType;
      do {
        questionType = kb.nextInt();
      } while(questionTypeCheck(questionType));
      kb.nextLine();

      // Set question prompt
      System.out.print("What is the question prompt?: ");
      String prompt = kb.nextLine();
      Question question;
      if(questionType == 0) {
        question = new SingleAnswer(prompt);
      } else {
        question = new MultipleAnswer(prompt);
      }

      //Set question
      iVote.setQuestion(question);

      // Set answer choices
      System.out.println("Type the answer choices, separated by semicolons. " +
          "They will be identified in iVote by the first character of the line, case sensitive.");
      String possibleAnswers = kb.nextLine();
      question.setPossibleAnswers(possibleAnswers);

      // Set correct answers
      if(questionType == 0) {
        System.out.print("What is the correct answer? " +
            "Please type the first character of the line of the correct answer, case sensitive.: ");
        question.setCorrectAnswer(kb.nextLine().trim().charAt(0));
      } else {
        System.out.print("What are the correct answers? Please type the first character of the line of each of the " +
            "correct answers, case sensitive, separated by semicolons.: ");
        try {
          question.setCorrectAnswers(kb.nextLine().trim());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      printQuestionToFile(question);

      // Enter random student answers
      for(Student student : iVote.getStudents()) {
        ArrayList<Character> answer = generateRandomAnswer(question.getPossibleAnswers(), questionType);
        student.setAnswer(answer);
        printToFile(student);
      }

      System.out.println("Students set answers.");

      //Print results
      System.out.println("All results: ");
      System.out.println(iVote.getAllResults());
      System.out.println("Number of correct answers: ");
      System.out.println(iVote.getCorrectResults());
      printResultsToFile();
      iVote.clear();
    }

    System.out.println("Thank you for using iVote.");
  }

  private static void fileDriver() throws IOException {
    FileReader fr = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(fr);
    Random rand = new Random();
    try {
      FileWriter fw = new FileWriter(new File("results.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //creates a random amount of students for the classroom and their IDs
    int numStudents = rand.nextInt(30);
    for(int i = 0; i < numStudents; i++) {
      String studentID = createRandomID();
      Student student = new Student(studentID);
      iVote.addStudent(student);
    }

    System.out.println("Created " + numStudents + " students.");

    System.out.println("How many questions do you want to enter?: ");
    int quizLength = Integer.parseInt(br.readLine());
    System.out.println("\tRead from file.");

    for(int i = 0; i < quizLength; i++) {
      System.out.println("Question " + (i+1) + ":");

      // Set question type
      System.out.println("Is this question single answer (please type 0) or multiple answer (please type 1)?: ");
      int questionType;
      do {
        questionType = Integer.parseInt(br.readLine());
        System.out.println("\tRead from file.");
      } while(questionTypeCheck(questionType));

      // Set question prompt
      System.out.println("What is the question prompt?: ");
      String prompt = br.readLine();
      System.out.println("\tRead from file.");
      Question question;
      if(questionType == 0) {
        question = new SingleAnswer(prompt);
      } else {
        question = new MultipleAnswer(prompt);
      }

      //Set question
      iVote.setQuestion(question);

      // Set answer choices
      System.out.println("Type the answer choices, separated by semicolons. " +
          "They will be identified in iVote by the first character of the line, case sensitive.");
      String possibleAnswers = br.readLine();
      question.setPossibleAnswers(possibleAnswers);
      System.out.println("\tRead from file.");

      // Set correct answers
      if(questionType == 0) {
        System.out.println("What is the correct answer? " +
            "Please type the first character of the line of the correct answer, case sensitive.: ");
        question.setCorrectAnswer(br.readLine().trim().charAt(0));
      } else {
        System.out.println("What are the correct answers? Please type the first character of the line of each of the " +
            "correct answers, case sensitive, separated by semicolons.: ");
        try {
          question.setCorrectAnswers(br.readLine().trim());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println("\tRead from file.");

      printQuestionToFile(question);

      // Enter random student answers
      for(Student student : iVote.getStudents()) {
        ArrayList<Character> answer = generateRandomAnswer(question.getPossibleAnswers(), questionType);
        student.setAnswer(answer);
        printToFile(student);
      }

      System.out.println("Students set answers.");

      //Print results
      System.out.println("All results: ");
      System.out.println(iVote.getAllResults());
      System.out.println("Number of correct answers: ");
      System.out.println(iVote.getCorrectResults());
      printResultsToFile();
      iVote.clear();
    }

    System.out.println("Thank you for using iVote.");
  }

  private static void printQuestionToFile(Question question) {
    try{
      FileWriter fw = new FileWriter(new File("results.txt"),true);
      fw.write("Question: " + question.getPrompt());
      fw.write("\nAnswer Choices: " + question.getPossibleAnswers());
      fw.write("\nCorrect Answer(s): " + question.getCorrectAnswers() + "\n");
      fw.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void printToFile(Student student) {
    try{
      FileWriter fw = new FileWriter(new File("results.txt"),true);
      fw.write("Student " + student.getID() + " answered: " + student.getAnswer().toString() + "\n");
      fw.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void printResultsToFile() {
    try{
      FileWriter fw = new FileWriter(new File("results.txt"),true);
      fw.write("All results: \n");
      fw.write(iVote.getAllResults());
      fw.write("\nNumber of correct answers: \n");
      fw.write(iVote.getCorrectResults());
      fw.write("\n");
      fw.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static ArrayList<Character> generateRandomAnswer(List<String> possibleAnswers, int questionType) {
    List<Character> randomAnswer = new ArrayList<>();
    String answers = "";
    for (String choices : possibleAnswers) {
      answers += choices.charAt(0);
    }
    Random rand = new Random();
    if(questionType == 0) {
      // if single choice, add only 1 answer
      randomAnswer.add(answers.charAt(rand.nextInt(answers.length())));
    } else {
      // else if multiple choice, add as many answers as you want
      // added +1 so students would at least guess 1 answer, without it they could choose not to answer
      // and we want to encourage at least guessing
      int numAnswers = rand.nextInt(answers.length()) + 1;
      for (int i = 0; i < numAnswers; i++) {
        char a = answers.charAt(rand.nextInt(answers.length()));
        if (!randomAnswer.contains(a)) {
          randomAnswer.add(a);
        }
      }
    }
    return (ArrayList<Character>)randomAnswer;
  }

  private static boolean questionTypeCheck(int questionType) {
    if(questionType != 0 && questionType != 1) {
      System.out.println("Invalid question type. Please type 0 for single answer, or 1 for multiple answer.: ");
      return true;
    }
    return false;
  }


  //creates a random student ID of 5 characters
  private static String createRandomID() {
    String randID;
    Random rand = new Random();
    char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++){
      char c = characters[rand.nextInt(characters.length)];
      sb.append(c);
    }
    randID = sb.toString();
    return randID;
  }

}
