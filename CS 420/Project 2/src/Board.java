import java.util.Arrays;

public class Board {

  private int[] board;
  private int pairs;

  //can assume each queen starts in separate columns
  public Board(int size) {
    this.board = new int[size];
    for( int i = 0; i < size; i++ ){
      board[i] = (int)( Math.random() * size );
    }
    this.pairs = countPairs();
  }

  public Board(int[] board) {
    this.board = board;
    this.pairs = countPairs();
  }

  private int countPairs() {
      int pairs = 0;
      for( int i = 0; i < board.length; i++ ){
        pairs += countAttacking( i, board[i] );
      }
      return pairs;
    }

  private int countAttacking(int column, int row) {
    int attackingPairs = 0;
    for (int i = column + 1; i < board.length; i++) {
      if (row == board[i]) {
        //same row
        attackingPairs++;
      } else if(Math.abs(column - i) == Math.abs(row - board[i])) {
        //same diagonal
        attackingPairs++;
      }
    }
    return attackingPairs;
  }

  public int[] nextState() {
    int diffPairs = 0;
    int columnToBeat = -1;
    int rowToBeat = -1;
    for(int i = 0; i < board.length; i++){
      int pairs = countAttacking(i, board[i]);
      for(int j = 0; j < board.length; j++){
        if(j != board[i]){
          int newPairs = countAttacking(i, j);
          if(newPairs < pairs && diffPairs < (pairs - newPairs)){
            diffPairs = pairs - newPairs;
            columnToBeat = i;
            rowToBeat = j;
          }
        }
      }
    }
    if( columnToBeat < 0 )
      return null;
    else{
      int[] nextBoard = Arrays.copyOf(board, board.length);
      nextBoard[columnToBeat] = rowToBeat;
      return nextBoard;
    }
  }

  public int getPairs() {
    return pairs;
  }

  public int[] getBoard() {
    return board;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(board);
  }

  @Override
  public boolean equals(Object obj) {
    return Arrays.equals(this.board, ((Board)obj).board);
  }
}
