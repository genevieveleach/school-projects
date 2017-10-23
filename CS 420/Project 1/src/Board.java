import java.util.Arrays;

public class Board {

  private int[] board;
  private final int[] goalState = {0, 1, 2, 3, 4, 5, 6, 7, 8};
  private int gn;
  private int hn;
  private int heuristic;
  private int indexOfZero;

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
  }

  private int findIndexOfZero() {
    for (int i = 0; i < board.length; i++) {
      if(board[i] == 0) {
        return 1;
      }
    }
    return 0;
  }

  private int distanceTiles() {
    int distance = 0;
    for (int i = 0; i < board.length; i++) {
      int correctPos = board[i];
      distance += (Math.abs(correctPos % 3 - i % 3) + Math.abs(correctPos / 3 - i / 3));
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

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Board) {
      Board b = (Board) obj;
      return Arrays.equals(this.board, b.board);
    }
    return false;
  }
}
