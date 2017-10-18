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

  private static short checksum(byte[] b) {
    //if the array length is odd, extend it and make it even, last value 0 because we compare pairs of bytes
    if((b.length % 2) != 0) {
      byte[] bOdd = new byte[b.length+1];
      System.arraycopy(b, 0, bOdd, 0, b.length);
      bOdd[bOdd.length-1] = 0;
      b = bOdd;
    }
    int sum = 0;
    for (int i = 0; (i + 1) < b.length; i += 2) {
      int first = b[i];
      // if value is negative, xor it to fix it
      if (first < 0) {
        first ^= 0xFFFFFF00;
      }
      int second = b[i+1];
      // if value is negative, xor it to fix it
      if (second < 0) {
        second^= 0xFFFFFF00;
      }
      // shift the first value left
      first <<= 8;
      // xor it with the second value then add to sum
      sum += (first ^ second);
      // overflow detection
      if ((sum & 0xFFFF0000) != 0) {
        /*carry occurred, so wrap around */
        sum &= 0xFFFF;
        sum++;
      }
    }
    // preform 1's complement and return the rightmost 16 bits of the sum
    return (short)(~(sum & 0xFFFF));
  }
}
