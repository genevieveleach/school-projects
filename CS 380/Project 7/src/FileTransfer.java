import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;

public class FileTransfer {

  public static void main(String[] args) {
    if (args.length > 0) {
      if (args[0].equals("makekeys")) {
        keygen();
      } else if (args[0].equalsIgnoreCase("server")) {
        runServer(args[1], Integer.parseInt(args[2]));
      } else if (args[0].equalsIgnoreCase("client")) {
        runClient(args[1], args[2], Integer.parseInt(args[3]));
      } else {
        System.out.println("Invalid args.");
      }
    } else {
      System.out.println("No args detected.");
    }
  }

  private static void runClient(String fileName, String hostAddr, int portNumber) {
  //TODO:
  }

  private static void runServer(String fileName, int portNumber) {
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      Socket clientSocket = serverSocket.accept();
      ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
      ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
      while(true) {
        Message input = (Message) ois.readObject();
        if(input.getType().equals(MessageType.DISCONNECT)) {
          PrintStream clientConsole = new PrintStream(clientSocket.getOutputStream(), true, "UTF-8");
          clientConsole.println("Disconnected from server.");
          clientSocket.close();
          serverSocket.close();
          break;
        } else if(input.getType().equals(MessageType.START)) {
          //TODO:
        } else if(input.getType().equals(MessageType.STOP)) {
          //TODO: 3. Stop Message
        } else if(input.getType().equals(MessageType.CHUNK)) {
          //TODO: 4. Chunk
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void keygen() {
    try {
      KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
      gen.initialize(4096); // you can use 2048 for faster key generation
      KeyPair keyPair = gen.genKeyPair();
      PrivateKey privateKey = keyPair.getPrivate();
      PublicKey publicKey = keyPair.getPublic();
      try (ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream(new File("public.bin")))) {
        oos.writeObject(publicKey);
      }
      try (ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream(new File("private.bin")))) {
        oos.writeObject(privateKey);
        System.out.println("Keys generated.");
      }
    } catch (NoSuchAlgorithmException | IOException e) {
      e.printStackTrace(System.err);
    }
  }


}
