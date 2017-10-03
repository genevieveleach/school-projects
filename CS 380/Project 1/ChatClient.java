import java.io.*;
import java.net.Socket;

public class ChatClient {

  public static void main(String[] args) {
      try {
      Socket socket = new Socket("18.221.102.182", 38001);
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader br = new BufferedReader(isr);

      OutputStream os = socket.getOutputStream();
      PrintStream out = new PrintStream(os, true, "UTF-8");
      BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        Runnable listener = () -> {
          while(socket.isConnected()) {
            try {
              System.out.println(br.readLine());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        };

      System.out.print("Username: ");
      out.println(kb.readLine());

      Thread read = new Thread(listener);
      read.start();

      while(socket.isConnected()) {
        out.println(kb.readLine());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
