
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    private static Socket socket;

    public static void main(String[] args) throws Exception {

        Runnable client = () -> {
            try {
                Socket clientSocket = socket;
                String address = clientSocket.getInetAddress().getHostAddress();
                System.out.printf("Client connected: %s%n", address);
                OutputStream os = clientSocket.getOutputStream();
                PrintStream out = new PrintStream(os, true, "UTF-8");
                out.printf("Hi %s, thanks for connecting!%n", address);

                InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String in = null;
                while (true) {
                    in = br.readLine();
                    if (in.trim().toLowerCase().equals("exit")) {
                        break;
                    }
                    out.println(in);
                }
                System.out.printf("Client disconnected: %s%n", address);
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        try(ServerSocket serverSocket = new ServerSocket(22222)) {
            while(true) {
                socket = serverSocket.accept();
                Thread newClient = new Thread(client);
                newClient.start();
            }
        }

    }
}