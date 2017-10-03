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
          System.out.println("Established server connection.");
          while(socket.isConnected()) {
            try {
              String in;
              in = br.readLine();
              if (in != null) {
                if (in.trim().equals("Name in use.")) {
                  System.out.println(in + " Please try another nickname.");
                  System.exit(0);
                } else if (in.trim().equals("Connection idle for 1 minute, closing connection.")) {
                  System.out.println(in);
                  System.exit(0);
                }
                System.out.println(in);
              } else {
                // do nothing
              }
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
