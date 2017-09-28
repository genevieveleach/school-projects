
import java.io.*;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Server> " + br.readLine());
            String kbIn;
            do {
                System.out.print("Client> ");
                kbIn = kb.readLine();
                out.println(kbIn);
                String SIn = br.readLine();
                if (SIn != null) {
                    System.out.println("Server> " + SIn);
                }
            } while(!kbIn.trim().toLowerCase().equals("exit"));
        }
    }
}