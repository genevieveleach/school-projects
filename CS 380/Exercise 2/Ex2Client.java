import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class Ex2Client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("18.221.102.182", 38102);
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader br = new BufferedReader(isr);
      OutputStream os = socket.getOutputStream();
      PrintStream out = new PrintStream(os, true, "UTF-8");

      int[] array = new int[100];
      byte[] bArray = new byte[100];
      System.out.print("Connected to server.\nReceived bytes:");
      for (int i = 0; i<array.length; i++) {
        int byteOne = br.read();
        int byteTwo = br.read();
        array[i] = (byteOne << 4) | byteTwo;
        if(i % 10 == 0) {
          //is beginning of line, format
          System.out.print("\n\t");
        }
        //print byte
        System.out.printf("%02X",array[i]);
        //add to byte array for CRC
        bArray[i] = (byte)array[i];
      }

      CRC32 error = new CRC32();
      error.update(bArray);
      long result = error.getValue();
      System.out.printf("Generated CRC32: %X\n", result);

      ByteBuffer buffer = ByteBuffer.allocate(4);
      buffer.putInt((int)result);
      byte[] output = buffer.array();
      out.write(output);

      int response = br.read();
      if(response == 1) {
        System.out.println("Response good.");
      } else {
        System.out.println("Bad response.");
      }
      socket.close();
      System.out.println("Disconnected from server.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
