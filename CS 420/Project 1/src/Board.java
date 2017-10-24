import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Board implements Comparable<Board> {

  private int[] board;
  private final int[] goalState = {0, 1, 2, 3, 4, 5, 6, 7, 8};
  private int gn;
  private int hn;
  private int heuristic;
  private int indexOfZero;
  private final Board parent;

  public Board(int[] board, int heuristic) throws Exception {
    gn = 0;
    this.board = board;
    this.heuristic = heuristic;
    if (heuristic == 1) {
      hn = misplacedTiles();
    } else if (heuristic == 2) {
      hn = distanceTiles();
    } else {
      throw new Exception("Not a valid heuristic; 1 for misplaced tiles and 2 for distance tiles.");
    }
    this.indexOfZero = findIndexOfZero();
    parent = null;
  }

  public Board(Board parent, int newIndexOfZero, int heuristic) {
    this.board = Arrays.copyOf(parent.board, parent.board.length);
    this.heuristic = heuristic;
    if (heuristic == 1) {
      hn = misplacedTiles();
    } else if (heuristic == 2) {
      hn = distanceTiles();
    }
    int temp = board[parent.indexOfZero];
    board[parent.indexOfZero] = board[newIndexOfZero];
    board[newIndexOfZero] = temp;
    this.indexOfZero = newIndexOfZero;
    this.gn = parent.gn + 1;
    this.parent = parent;
  }

  public Board() {
    do {
      ArrayList<Integer> numberList = new ArrayList<Integer>( Arrays.asList( 0, 1, 2, 3, 4, 5, 6, 7, 8 ) );
      board = new int[9];
      for( int i = 0; i < board.length; ++i )
      {
        board[i] = numberList.remove((int)(Math.random() * numberList.size()));
      }
    }
    while(!isValid());
    gn = 0;
    this.indexOfZero = findIndexOfZero();
    parent = null;
  }

  public void setHeuristic(int heuristic) {
    this.heuristic = heuristic;
    if (heuristic == 1) {
      hn = misplacedTiles();
    } else if (heuristic == 2) {
      hn = distanceTiles();
    }
  }

  private int findIndexOfZero() {
    for (int i = 0; i < board.length; i++) {
      if(board[i] == 0) {
        return i;
      }
    }
    return -1;
  }

  public int getFn() {
    return this.gn + this.hn;
  }

  private int distanceTiles() {
    int distance = 0;
    for (int i = 0; i < board.length; i++) {
      int pos = board[i];
      distance += (Math.abs(pos % 3 - i % 3) + Math.abs(pos / 3 - i / 3));
    }
    return distance;
  }

  private int misplacedTiles() {
    int misplacedTiles = 0;
    for (int i = 0; i < board.length; i++) {
      if (board[i] != goalState[i]) {
        misplacedTiles++;
      }
    }
    return misplacedTiles;
  }

  public boolean isGoalState() {
    return Arrays.equals(this.board, goalState);
  }

  public boolean isValid() {
    int inversions = 0;
    for (int i = 0; i < board.length - 1; i++) {
      for (int j = i + 1; j < board.length; j++) {
        if (board[i] > board[j] && board[i] != 0 && board[j] !=0) {
          inversions++;
        }
      }
    }
    return (inversions % 2 == 0);
  }


  public ArrayList<Board> getSuccessors() {
    ArrayList<Board> successors = new ArrayList<>();
    int zeroRow = indexOfZero / 3;
    int zeroColumn = indexOfZero % 3;
    for (int i = 0; i < board.length; i++) {
      int currentRow = i / 3;
      int currentColumn = i % 3;
      if ((Math.abs(currentRow - zeroRow) + Math.abs(currentColumn - zeroColumn)) == 1) {
        Board b = new Board(this, i, heuristic);
        successors.add(b);
      }
    }
    return successors;
  }

  public int getHeuristic() {
    return heuristic;
  }

  public int getGn() {
    return gn;
  }

  public String printBoard() {
    String string = "";
    for(int i = 0; i < board.length; i++) {
      string += board[i] + "\t";
      if((i + 1) % 3 == 0 && i != 0 & i != 8) {
        string+= "\n";
      }
    }
    return string;
  }

  public void printStates() {
    if(parent != null) {
      parent.printStates();
    }
    System.out.println();
    System.out.println(this.toString());
  }

  public int[] getBoard() {
    return board;
  }

  @Override
  public String toString() {
    return "f(n) = " + getFn() + ", g(n) = " + gn + ", h(n) = " + hn + " \n" + this.printBoard();
  }

  @Override
  public int compareTo(Board b) {
    if (this.getFn() < b.getFn()) {
      return -1;
    }
    if (this.getFn() > b.getFn()) {
      return 1;
    }
    return 0;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(board);
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Board) {
      Board b = (Board) obj;
      return Arrays.equals(this.board, b.board);
    }
    return false;
  }
}
