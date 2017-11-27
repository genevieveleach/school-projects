import javax.crypto.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.CRC32;

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

  private static void runServer(String fileName, int portNumber) {
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      Socket clientSocket = serverSocket.accept();
      System.out.println("Client connected.");
      ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
      ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
      System.out.println("Created ois and oos.");
      Key sessionKey = null;
      int chunkAmount = 0;
      int seqNum = -1;
      while(true) {
        Message input = (Message) ois.readObject();
        if(input.getType().equals(MessageType.DISCONNECT)) {
          PrintStream clientConsole = new PrintStream(clientSocket.getOutputStream(), true, "UTF-8");
          clientConsole.println("Disconnected from server.");
          clientSocket.close();
          serverSocket.close();
          break;
        } else if(input.getType().equals(MessageType.START)) {
          try {
            chunkAmount = (int) (((StartMessage)input).getSize() / ((StartMessage)input).getChunkSize());
            ObjectInputStream fileIS = new ObjectInputStream(new FileInputStream(fileName));
            PrivateKey privateKey = (PrivateKey) fileIS.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, privateKey);
            sessionKey = cipher.unwrap(((StartMessage)input).getEncryptedKey(), "AES", Cipher.SECRET_KEY);
            seqNum = 0;
            oos.writeObject(new AckMessage(seqNum));
          } catch (Exception e) {
            seqNum = -1;
            oos.writeObject(new AckMessage(seqNum));
          }
        } else if(input.getType().equals(MessageType.STOP)) {
          seqNum = -1;
          oos.writeObject(new AckMessage(seqNum));
        } else if(input.getType().equals(MessageType.CHUNK)) {
          if(seqNum == ((Chunk)input).getSeq()) {
            if(seqNum < chunkAmount) {
              Cipher cipher = Cipher.getInstance("AES");
              cipher.init(Cipher.DECRYPT_MODE, sessionKey);
              byte[] decryptedData = cipher.doFinal(((Chunk)input).getData());
              CRC32 crc = new CRC32();
              crc.update(decryptedData);
              if(crc.getValue() == ((Chunk)input).getCrc()) {
                seqNum++;
                if(seqNum == 1) {
                  new FileOutputStream("test.txt").write(decryptedData);
                } else {
                  new FileOutputStream("test.txt", true).write(decryptedData);
                }
                System.out.printf("Chunk recieved[%d/%d].\n", seqNum, chunkAmount);
                oos.writeObject(new AckMessage(seqNum));
              }
              if(seqNum == chunkAmount) {
                System.out.println("Transfer complete.");
                System.out.println("Output path: test.txt");
                sessionKey = null;
                chunkAmount = 0;
                seqNum = -1;
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void runClient(String fileName, String hostAddr, int portNumber) {
    Scanner kb = new Scanner(System.in);
    try {
      Socket socket = new Socket(hostAddr, portNumber);
      System.out.println("Connected to server: " + hostAddr + "/" + socket.getInetAddress().getHostAddress());;
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Created ois and oos.");
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      keygen.init(128);
      SecretKey sessionKey = keygen.generateKey();
      System.out.println("Generated session key.");
      Cipher cipher = Cipher.getInstance("RSA");
      PublicKey publicKey = (PublicKey) new ObjectInputStream(new FileInputStream(fileName)).readObject();
      cipher.init(Cipher.WRAP_MODE, publicKey);
      byte[] wrappedKey = cipher.wrap(sessionKey);
      System.out.println("Key wrapped.");
      while(true) {
        System.out.printf("Enter path: ");
        String path = kb.nextLine();
        if(!new File(path).exists()) {
          throw new Exception("Invalid path.");
        }
        int chunkSize = 1024;
        try {
          System.out.printf("Enter chunk size [1024]: ");
          chunkSize = kb.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Incorrect input, defaulting to 1024.");
        }
        StartMessage startMessage = new StartMessage(path, wrappedKey, chunkSize);
        oos.writeObject(startMessage);
        int chunkAmount = (int) (startMessage.getSize()/startMessage.getChunkSize());
        int seqNum = ((AckMessage)ois.readObject()).getSeq();
        if(seqNum == 0) {
          File file = new File(path);
          System.out.printf("Sending: %s. File size: %d\nSending %d chunks.\n", path, file.length(), chunkAmount);
          FileInputStream fileIS = new FileInputStream(file);
          Cipher encrypt = Cipher.getInstance("AES");
          encrypt.init(Cipher.ENCRYPT_MODE, sessionKey);
          while(seqNum < chunkAmount) {
            byte[] data = new byte[startMessage.getChunkSize()];
            fileIS.read(data);
            CRC32 crc = new CRC32();
            crc.update(data);
            byte[] encryptedData = encrypt.doFinal(data);
            oos.writeObject(new Chunk(seqNum, encryptedData, (int)crc.getValue()));
            seqNum = (((AckMessage) ois.readObject()).getSeq());
            System.out.printf("Chunks completed [%d/%d].\n", seqNum, chunkAmount);
          }
        }
        System.out.printf("Would you like to TRANSFER a new file or DISCONNECT: ");
        String choice = kb.nextLine();
        if(choice.equalsIgnoreCase("TRANSFER")) {
          System.out.println("Transferring another file...");
        } else if(choice.equalsIgnoreCase("DISCONNECT")) {
          System.out.println("Disconnecting.");
          oos.writeObject(new DisconnectMessage());
          socket.close();
          break;
        } else {
          System.out.println("Invalid input. Disconnecting.");
          oos.writeObject(new DisconnectMessage());
          socket.close();
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
