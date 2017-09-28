
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientThread thread = new ClientThread(socket);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ClientThread extends Thread {

        Socket client;

        ClientThread(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                String address = client.getInetAddress().getHostAddress();
                System.out.printf("Client connected: %s%n", address);
                OutputStream os = client.getOutputStream();
                PrintStream out = new PrintStream(os, true, "UTF-8");
                out.printf("Hi %s, thanks for connecting!%n", address);

                InputStream is = client.getInputStream();
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
                client.close();
                System.out.printf("Client disconnected: %s%n", address);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}