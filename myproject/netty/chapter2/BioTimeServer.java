package netty.chapter2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio的局限 accept方法会阻塞，不用多线程的话就很拥堵
 * 用多线程的话如下案例，每次有服务端连接时必须创建线程去处理
 */
public class BioTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
