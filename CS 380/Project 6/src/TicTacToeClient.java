import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToeClient {

  public static void main(String[] args) {
    try (Socket socket = new Socket("18.221.102.182", 38006)) {
      ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
      Scanner kb  = new Scanner(System.in);
      System.out.printf("Username: ");
      String username = kb.nextLine();
      ConnectMessage connection = new ConnectMessage(username);
      os.writeObject(connection);
      CommandMessage begin = new CommandMessage(CommandMessage.Command.NEW_GAME);
      os.writeObject(begin);

      BoardMessage response = (BoardMessage) is.readObject();
      boolean errorAvoided = true;
      while (response.getStatus() == BoardMessage.Status.IN_PROGRESS && errorAvoided) {
        byte[][] board = response.getBoard();
        char[][] ticTicToeBoard = convertToChar(board);

        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            System.out.print(ticTicToeBoard[i][j] + " ");
          }
          System.out.println();
        }

        //TODO: play game

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static char[][] convertToChar(byte[][] board) {
    char[][] ticTacToeBoard = new char[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if(board[i][j] == 0) {
          ticTacToeBoard[i][j] = '　';
        } else if (board[i][j] == 1) {
          ticTacToeBoard[i][j] = 'X';
        } else {
          ticTacToeBoard[i][j] = 'O';
        }
      }
    }
    return ticTacToeBoard;
  }
}
