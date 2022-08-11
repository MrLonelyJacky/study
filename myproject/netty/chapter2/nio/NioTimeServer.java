package netty.chapter2.nio;

public class NioTimeServer {
    public static void main(String[] args) {
        int port =8080;
        MutiplexerTimeServer timeServer = new MutiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
