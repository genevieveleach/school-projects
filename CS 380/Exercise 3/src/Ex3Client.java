import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Ex3Client {

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("18.221.102.182", 38103);
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      System.out.println("Connected to server.");

      int amountOfBytes = is.read();
      byte[] readBytes = new byte[amountOfBytes];
      System.out.println("Reading " + amountOfBytes + " bytes.");
      System.out.print("Data received:");
      for(int i = 0; i < amountOfBytes; i++) {
        int value = is.read();
        readBytes[i] = (byte) value;
        if ((i % 10) == 0) {
          System.out.print("\n\t");
        }
        System.out.printf("%02X", readBytes[i]);
      }
      short checksum = checksum(readBytes);
      System.out.println("\nChecksum calculated: 0x" + String.format("%04X", checksum));
      ByteBuffer buffer = ByteBuffer.allocate(2);
      buffer.putShort(checksum);
      byte[] output = buffer.array();
      os.write(output);
      int response = is.read();
      if(response == 1) {
        System.out.println("Response good.");
      } else {
        System.out.println("Bad response.");
      }
      socket.close();
      System.out.println("Disconnected from server.");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  //TODO: fix
  private static short checksum(byte[] b) {
    long sum = 0;
    for (int i = 0; i < b.length; i++) {
      sum += b[i];
      if ((sum & 0xFFFF0000) != 0) {
        /*carry occurred, so wrap around */
        sum &= 0xFFFF;
        sum++;
      }
    }
    return (short)(~(sum & 0xFFFF));
  }
}
