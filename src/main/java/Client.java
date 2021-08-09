import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException {
        String host= "netology.homework";
        int port= 8090;
        InetAddress inetAddress = InetAddress.getByName(host);
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("Hi!");
            String resp;
            while (true) {
                String answer = "empty";
                resp = in.readLine();
                System.out.println("> " + resp);
                if (resp.contains("What is your name?")) {
                    answer = "Pasha";
                } else if (resp.contains("Where are you from?")){
                    answer = "Russia";
                }else if (resp.contains("Are you child? (yes/no)")){
                    answer = "yes";
                } else if (resp.contains("/end")){
                    break;
                }
                System.out.println("< " + answer);
                out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
