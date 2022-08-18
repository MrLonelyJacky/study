package netty.chapter2.nio;

public class NioClient {

    public static void main(String[] args) {
        int port = 8080;
        NioClientHandler handler = new NioClientHandler("localhost", port, "TimeClient-001");

        new Thread(handler, "TimeClient-001").start();
        /*new Thread(new NioClientHandler("localhost", port,"TimeClient-002"), "TimeClient-002").start();
        new Thread(new NioClientHandler("localhost", port,"TimeClient-003"), "TimeClient-003").start();*/
    }
}
