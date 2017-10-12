import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class PhysLayerClient {

  private static HashMap<String, String> table;

  public static void main(String[] args) {
    initTable();
    try {
      Socket socket = new Socket("18.221.102.182", 38002);
      System.out.println("Connected to server.");

      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      double baseline = 0.0;
      for (int i = 0; i < 64; i++) {
        int signal = is.read();
        baseline += signal;
      }
      baseline /= 64;
      System.out.printf("Baseline established from preamble: %.2f\n", baseline);

      int[] input = new int[320];
      for(int i = 0; i < 320; i++) {
        input[i] = is.read();
      }

      String encoded = "";
      for (int i: input) {
        if(i < baseline) {
          encoded += 0;
        } else {
          encoded += 1;
        }
      }

      String decoded = decode(encoded);
      String fourB = "";
      for(int i = 0; i < decoded.length(); i+=5) {
        // each substring of 5 bits will be converted into 4B
        fourB += conversionFiveToFour(decoded.substring(i, i+5));
      }

      //combine the 64 half bytes into full, 8 bit bytes
      int[] fullBytes = conversionFullBytes(fourB);
      System.out.print("Received 32 bytes: ");
      byte[] outBytes = new byte[32];
      for (int i = 0; i < fullBytes.length; i++) {
        outBytes[i] = (byte)fullBytes[i];
        System.out.printf("%X", outBytes[i]);
      }
      System.out.println();
      os.write(outBytes);
      int response = is.read();
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

  // decodes the NRZI
  private static String decode(String encoded) {
    String decoded = "";
    char prev = '0';
    for(char current:encoded.toCharArray()) {
        if (current == prev) {
          decoded += 0;
        } else {
          decoded += 1;
        }
        prev = current;
    }
    return decoded;
  }

  // combines the half bytes to full bytes
  private static int[] conversionFullBytes(String fourB) {
    int[] bytes = new int[32];
    for (int i = 0, j = 0; i < fourB.length() && j < 32; i+=8, j++) {
      int byteOne = Integer.parseInt(fourB.substring(i, i+4), 2);
      int byteTwo = Integer.parseInt(fourB.substring(i+4, i+8), 2);
      bytes[j] = (byteOne << 4) | byteTwo;
    }
    return bytes;
  }

  // converts from 5B to 4B
  private static String conversionFiveToFour(String in) throws Exception {
    String fourB = "";
    String value = table.get(in);
      if(value == null) {
        throw new Exception("Not a valid 5B string: " + in);
      }
      fourB += value;
    return fourB;
  }

  // initializes the 5B/4B table
  private static void initTable() {
    table = new HashMap<String, String>();
    String[] fiveBSide = {"11110", "01001", "10100", "10101",
                          "01010", "01011", "01110", "01111",
                          "10010", "10011", "10110", "10111",
                          "11010", "11011", "11100", "11101"};
    for (int i = 0; i < fiveBSide.length; i++) {
      String binary = Integer.toBinaryString(i);
      String st = String.format("%4s", binary).replace(" ", "0");
      table.put(fiveBSide[i], st);
    }
  }
}
