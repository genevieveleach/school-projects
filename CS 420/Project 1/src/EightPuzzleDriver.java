import java.io.*;
import java.util.*;

public class EightPuzzleDriver {

  private static final boolean TESTING = false;
  private static final int NUMTESTS = 101;
  private static AStar aStar = new AStar();

  public static void main(String[] args) throws Exception {
    Scanner kb = new Scanner(System.in);
    System.out.println("Welcome to the 8-Puzzle.");
    System.out.println("Please choose an option: ");
    System.out.println("1: Randomly generate an 8-puzzle");
    System.out.println("2: Enter your own 8-puzzle");
    System.out.println("3: Read from file");
    if(TESTING) {
      System.out.printf("Answer overridden, randomly generating %d test cases.\n", NUMTESTS);
      randomlyGenerateTests();
    } else {
      int choice;
      choice = kb.nextInt();
      if (choice == 1) {
        randomPuzzle();
      } else if (choice == 2) {
        userInputPuzzle();
      } else if (choice == 3) {
        readPuzzleFromFile();
      } else {
        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
      }
    }
  }

  private static void randomlyGenerateTests() throws Exception {
    HashMap<Integer, Integer> resultsMisplaced = new HashMap<>();
    HashMap<Integer, Integer> countMisplaced = new HashMap<>();
    HashMap<Integer, Integer> resultsManhattan = new HashMap<>();
    HashMap<Integer, Integer> countManhattan = new HashMap<>();
    initTimeFile();
    Board[] problemSet = new Board[NUMTESTS];
    for(int i = 0; i < NUMTESTS; i++) {
      problemSet[i] = new Board();
    }
    System.out.println("All boards generated.");
    for (Board board : problemSet) {
      board.setHeuristic(1);
      System.out.printf("Solving %s\n", Arrays.toString(board.getBoard()));
      HashMap<Integer, Integer> temp = aStar.aStarSearch(board);
      Set<Integer> keys = temp.keySet();
      for (int i : keys) {
        int tempNodes = temp.get(i);
        resultsMisplaced.compute(i, (k, v) -> v == null ? tempNodes : v + tempNodes);
        countMisplaced.compute(i, (k, v) -> v == null ? 1 : v + 1);
      }
      board.setHeuristic(2);
      temp = aStar.aStarSearch(board);
      keys = temp.keySet();
      for (int i : keys) {
        int tempNodes = temp.get(i);
        resultsManhattan.compute(i, (k, v) -> v == null ? tempNodes : v + tempNodes);
        countManhattan.compute(i, (k, v) -> v == null ? 1 : v + 1);
      }
    }

    System.out.println("Solved all tests.");
    System.out.println("Average Nodes Generated Per Depth for Misplaced Tiles Heuristic:");
    Set<Integer> keys = resultsMisplaced.keySet();
    for (int i : keys) {
      System.out.printf("Depth %d: %.2f\tCount: %d\n", i, ((double)resultsMisplaced.get(i)/countMisplaced.get(i)), countMisplaced.get(i));
    }
    System.out.println("Average Nodes Generated Per Depth for Manhattan Distance Heuristic:");
    keys = resultsManhattan.keySet();
    for (int i : keys) {
      System.out.printf("Depth %d: %.2f\tCount: %d\n", i, ((double)resultsManhattan.get(i)/countManhattan.get(i)), countManhattan.get(i));
    }
    printTimeToFile(aStar.timesMisplaced, aStar.timesManhattan, countMisplaced, countManhattan);
    System.out.println("Times printed to files.");
  }


  private static void readPuzzleFromFile() throws Exception {
    HashMap<Integer, Integer> resultsMisplaced = new HashMap<>();
    HashMap<Integer, Integer> countMisplaced = new HashMap<>();
    HashMap<Integer, Integer> resultsManhattan = new HashMap<>();
    HashMap<Integer, Integer> countManhattan = new HashMap<>();
    Scanner kb = new Scanner(System.in);
    System.out.println("What is the name of the file you would like to read from? Please input as: filename.fileextension");
    String file = kb.nextLine();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;
    System.out.println("Found file.");
    while((line = br.readLine()) != null) {
      if (line.contains("Depth")) {
        System.out.println(line);
      } else {
        int[] temp = new int[9];
        for (int i = 0; i < line.length(); i++) {
          String num = String.valueOf(line.charAt(i));
          if(num.equals(" ")) {
            continue;
          }
          temp[i] = Integer.parseInt(num);
        }
        Board board = new Board(temp, 1);
        System.out.printf("Solving %s\n", Arrays.toString(board.getBoard()));
        HashMap<Integer, Integer> tempMap = aStar.aStarSearch(board);
        Set<Integer> keys = tempMap.keySet();
        for (int i : keys) {
          int tempNodes = tempMap.get(i);
          resultsMisplaced.compute(i, (k, v) -> v == null ? tempNodes : v + tempNodes);
          countMisplaced.compute(i, (k, v) -> v == null ? 1 : v + 1);
        }
        board = new Board(temp, 2);
        tempMap = aStar.aStarSearch(board);
        keys = tempMap.keySet();
        for (int i : keys) {
          int tempNodes = tempMap.get(i);
          resultsManhattan.compute(i, (k, v) -> v == null ? tempNodes : v + tempNodes);
          countManhattan.compute(i, (k, v) -> v == null ? 1 : v + 1);
        }
      }
    }

    System.out.println("Solved all puzzles in file.");
    System.out.println("Average Nodes Generated Per Depth for Misplaced Tiles Heuristic:");
    Set<Integer> keys = resultsMisplaced.keySet();
    List<Integer> toSort = new ArrayList<>();
    toSort.addAll(keys);
    Collections.sort(toSort);
    for (int i : toSort) {
      System.out.printf("Depth %d: %.2f\tCount: %d\n", i, ((double) resultsMisplaced.get(i) / countMisplaced.get(i)), countMisplaced.get(i));
    }
    toSort.clear();
    System.out.println("Average Nodes Generated Per Depth for Manhattan Distance Heuristic:");
    keys = resultsManhattan.keySet();
    toSort.addAll(keys);
    Collections.sort(toSort);
    for (int i : toSort) {
      System.out.printf("Depth %d: %.2f\tCount: %d\n", i, ((double) resultsManhattan.get(i) / countManhattan.get(i)), countManhattan.get(i));
    }
    printTimeToFile(aStar.timesMisplaced, aStar.timesManhattan, countMisplaced, countManhattan);
    System.out.println("Times printed to file.");
  }

  private static void userInputPuzzle() throws Exception {
    Scanner kb = new Scanner(System.in);
    int[] puzzle = new int[9];
    boolean[] containsValues = new boolean[9];
    System.out.println("Here are the position numbers for the puzzle:");
    System.out.println("0\t1\t2");
    System.out.println("3\t4\t5");
    System.out.println("6\t7\t8");
    System.out.println("Please enter the digits in order, including 0 as the blank tile.");
    for (int i = 0; i < puzzle.length; i++) {
      System.out.println("Please enter digit for position " + i + ":");
      int input = kb.nextInt();
      puzzle[i] = input;
    }
    Board board = new Board(puzzle, 1);
    if (!board.isValid()) {
      System.out.println("The entered puzzle is not able to be solved.");
      return;
    } else {
      aStar.aStarSearch(board);
    }
    board = new Board(puzzle, 2);
    if (!board.isValid()) {
      System.out.println("The entered puzzle is not able to be solved.");
    } else {
      aStar.aStarSearch(board);
    }
  }

  private static void randomPuzzle() throws Exception {
    Board randomBoard = new Board();
    System.out.println("Generated random board: " + Arrays.toString(randomBoard.getBoard()));
    randomBoard.setHeuristic(1);
    aStar.aStarSearch(randomBoard);
    randomBoard.setHeuristic(2);
    aStar.aStarSearch(randomBoard);
  }

  private static void initTimeFile() throws IOException {
    FileWriter f = new FileWriter(new File("outputTimeMisplaced.txt"), false);
    f.write("Average Times:");
    f.close();
    f = new FileWriter(new File("outputTimeManhattan.txt"), false);
    f.write("Average Times:");
    f.close();
  }

  private static void printTimeToFile(HashMap<Integer, Long> timeMisplaced, HashMap<Integer, Long> timeManhattan, HashMap<Integer, Integer> countMisplaced, HashMap<Integer, Integer> countManhattan) throws Exception {
    try {
      FileWriter f = new FileWriter(new File("outputTimeMisplaced.txt"), true);
      Set<Integer> keys = timeMisplaced.keySet();
      List<Integer> toSort = new ArrayList<>();
      toSort.addAll(keys);
      Collections.sort(toSort);
      for (int i : toSort) {
        f.write("\nDepth "+ i + ": " + ((double) timeMisplaced.get(i) / countMisplaced.get(i)));
      }
      f.close();
      f = new FileWriter(new File("outputTimeManhattan.txt"), true);
      keys = timeManhattan.keySet();
      toSort.clear();
      toSort.addAll(keys);
      Collections.sort(toSort);
      for (int i : toSort) {
        f.write("\nDepth "+ i + ": " + ((double) timeManhattan.get(i) / countManhattan.get(i)));
      }
      f.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
