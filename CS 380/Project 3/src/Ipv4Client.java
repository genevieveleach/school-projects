import java.io.IOException;
import java.net.Socket;

public class Ipv4Client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("18.221.102.182", 38003);
      
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
