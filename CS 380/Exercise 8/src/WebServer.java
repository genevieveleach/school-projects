import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8080);
    while(true) {
      Socket clientSocket  = serverSocket.accept();
      InputStream is = clientSocket.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      OutputStream os = clientSocket.getOutputStream();
      PrintWriter pw = new PrintWriter(os, true);

      Runnable runnable = () -> {
        try {
          String read = br.readLine();
          String fileName = "www" + read.split(" ")[1];
          System.out.println(fileName);
          File file = new File(fileName);
          BufferedReader fileBR = new BufferedReader(new FileReader(file));
          if(fileName.equals("www/hello.html")) {
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-type: test/html");
            pw.println("Content-length: " + file.length());
            read = fileBR.readLine();
            while(read != null) {
              pw.println(read);
              read = fileBR.readLine();
            }
            fileBR.close();
            pw.close();
          } else {
            pw.println("HTTP/1.1 404 Not Found");
            pw.println("Content-type: text/html");
            pw.println("Content length: " + file.length());
            read = fileBR.readLine();
            while(read != null) {
              pw.println(read);
              read = fileBR.readLine();
            }
            fileBR.close();
            pw.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      };

      Thread thread = new Thread(runnable);
      thread.start();
    }
  }
}
