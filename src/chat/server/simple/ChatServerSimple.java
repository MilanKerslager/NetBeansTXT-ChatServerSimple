// jednoduchý server schopný přijmou jen jednou jednoho klienta
// server opakuje, co klient píše
// pro připojení lze využít PuTTY (raw spojení na localhost, port 3333)

package chat.server.simple;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ChatServerSimple {

    public static void main(String[] args) {
        int port = 3333;
        try {
            System.out.println("Připojuji se na port: " + port);
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server běží: " + server);
            System.out.println("Čekám na klienta...");
            Socket socket = server.accept();
            System.out.println("Klient přijat: " + socket);
            DataInputStream streamIn;
            streamIn = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            while (true) {
                String line = streamIn.readUTF();
                System.out.println(line);
                if (line.equals("QUIT")) {
                    break;
                }
            }
            socket.close();
            streamIn.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatServerSimple.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
