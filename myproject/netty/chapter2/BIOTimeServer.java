package netty.chapter2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOTimeServer {

    public static void main(String[] args) {
        int port =8080;
        ServerSocket server =null;
        try {
            server = new ServerSocket(port);
            while (true){
                Socket accept = server.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
