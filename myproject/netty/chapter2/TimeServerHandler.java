package netty.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandler implements Runnable {

    private Socket socket;


    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            while (true){
                String body = in.readLine();
                if (body==null){
                    break;
                }
                System.out.println("receive message :"+body);
                out.println(System.currentTimeMillis());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
