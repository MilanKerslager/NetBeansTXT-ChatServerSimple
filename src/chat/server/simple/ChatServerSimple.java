// Simple Chat server with socket
// Server accepts one client at a time and then ends.
// For testing use PuTTY, connect to localhost, port 3333, raw connection.
// To end the session, write QUIT to the server alone on the line.

package chat.server.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServerSimple {

    public static void main(String[] args) {
        final int PORT = 3333;
        BufferedReader streamIn;
        try {
            System.out.println("Simple Chat server started and binded to port: " + PORT);
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server is running and waiting: " + server);
            Socket socket = server.accept();
            System.out.println("Client accepted at: " + socket);
            streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String line = streamIn.readLine();
                System.out.println(line);
                if (line.equals("QUIT")) {
                    break;
                }
            }
            System.out.println("Server ends now. Bye.");
            socket.close();
            streamIn.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatServerSimple.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
