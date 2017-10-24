import java.io.*;
import java.net.Socket;

public class Ipv4Client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("18.221.102.182", 38003);
      System.out.println("Connected to server.");

      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader br = new BufferedReader(isr);
      OutputStream os = socket.getOutputStream();
      for(int i = 2; i < Math.pow(2,12); i+=2) {
        System.out.println("Data length: " + i);
        byte[] header = new byte[/*size*/];
        int version = 4;
        int hLen = /*header length*/;

        //TOS: do not implement

        //length: implement

        //ident: do not implement

        //flags: implement assuming no fragmentation

        //offset: do not implement

        //TTL: implement assuming every packet has a TTL of 50

        //protocol: implement assuming TCP for all packets

        //checksum: implement

        //sourceaddr: implement using IP address of server

        //options/pad: ignore, dont even put in header

        //data: implement using 0's or random data

        String response = br.readLine();
        System.out.println(response);
        if(!response.equals("good")) {
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
