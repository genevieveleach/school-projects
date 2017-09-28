
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {

        Runnable client = () -> {
            {
                try {
                    while (true) {
                        try (Socket socket = serverSocket.accept()) {
                            String address = socket.getInetAddress().getHostAddress();
                            System.out.printf("Client connected: %s%n", address);
                            OutputStream os = socket.getOutputStream();
                            PrintStream out = new PrintStream(os, true, "UTF-8");
                            out.printf("Hi %s, thanks for connecting!%n", address);

                            InputStream is = socket.getInputStream();
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        serverSocket = new ServerSocket(22222);
        try {
            while (true) {
                Thread newClient = new Thread(client);
                newClient.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}