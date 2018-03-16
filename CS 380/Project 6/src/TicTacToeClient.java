import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeClient {

  private static boolean inProgress = false;

  public static void main(String[] args) throws Exception {
    try (Socket socket = new Socket("18.221.102.182", 38006)) {
      ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
      Scanner kb  = new Scanner(System.in);

      Thread thread = new Thread(() -> {
        try {
          while (true) {
            Object serverMessage = is.readObject();

            if (serverMessage instanceof Message) {
              if (!(serverMessage instanceof ErrorMessage) && !(serverMessage instanceof BoardMessage))
                System.out.println(serverMessage);
            }

            if (serverMessage instanceof ErrorMessage) {
              System.out.println();
              System.out.println(((ErrorMessage) serverMessage).getError());
              System.out.println();
            }

            if (serverMessage instanceof BoardMessage) {
              BoardMessage board = (BoardMessage) serverMessage;
              System.out.println("Turn: " + board.getTurn());
              char[][] cboard = convertToChar(board.getBoard());
              System.out.println("Board:");
              for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                  System.out.print(cboard[i][j] + " ");
                }
                System.out.println(Arrays.deepToString(cboard));
                System.out.println();
              }

              if (board.getStatus() == BoardMessage.Status.IN_PROGRESS) {
                inProgress = true;
              } else {
                System.out.println(board.getStatus());
                inProgress = false;
                System.out.println("Goodbye!");
                socket.close();
                System.exit(0);
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      System.out.printf("Username: ");
      String username = kb.nextLine();
      ConnectMessage connection = new ConnectMessage(username);
      os.writeObject(connection);
      CommandMessage begin = new CommandMessage(CommandMessage.Command.NEW_GAME);
      os.writeObject(begin);
      System.out.println("Game begun!");
      inProgress = true;
      thread.run();
      while(inProgress) {
        byte row = -1;
        byte column = -1;
        while(row < 0 || row > 2) {
          System.out.print("Choose a row 0-2 on the board to make your move: ");
          row = kb.nextByte();
          kb.nextLine();
        }
        while(column < 0 || column > 2) {
          System.out.print("Choose a column 0-2 on the board to make your move:");
          column = kb.nextByte();
          kb.nextLine();
        }
        MoveMessage move = new MoveMessage(row, column);
        os.writeObject(move);
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
