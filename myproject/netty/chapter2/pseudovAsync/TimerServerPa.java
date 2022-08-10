package netty.chapter2.pseudovAsync;

import netty.chapter2.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 采用线程池实现伪异步操作
 * 缺点：read，write都是阻塞操作，无论客户端和服务器，
 * 当服务器故障，处理速度很慢时大量线程会阻塞在队列中，熟悉tcp的朋友知道，如果客户端发送给
 * 服务端数据，服务端处理很慢，就会导致一直阻塞，双方处于keep-alive状态，这个想想之前遇到
 * 的超时的bug就知道了
 */
public class TimerServerPa {
    public static void main(String[] args) throws IOException {
        TimeHandlerExecutePool executePool = new TimeHandlerExecutePool(16,30);
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                executePool.execute(new TimeServerHandler(socket));
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
