import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8090;
        Socket clientSocket;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("New connection accepted");
            String string = in.readLine();
            out.println("Hi. What is your name?");
            final String name = in.readLine();
            out.println(String.format("Hi %s, Where are you from?", name));
            final String from = in.readLine();
            out.println(String.format("Hi %s from %s, Are you child? (yes/no)", name, from));
            final boolean itsChild = in.readLine().equals("yes");
            if (itsChild){
                out.println(String.format("Welcome to the kids area, %s from %s! Let's play! /end", name, from));
            } else {
                out.println(String.format("Welcome to the adult zone, %s from %s! Have a good rest, or a good working day! /end", name, from));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
