package nio.chapter5;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class TestBlockChannel {

    @Test
    public void serverTest1() throws IOException {
        ServerSocketChannel serverSocketChanne = ServerSocketChannel.open();
        System.out.println("默认的阻塞模式是："+serverSocketChanne.isBlocking());
        serverSocketChanne.bind(new InetSocketAddress("localhost",8090));
        System.out.println("begin "+System.currentTimeMillis());
        SocketChannel accept = serverSocketChanne.accept();
        System.out.println("end "+System.currentTimeMillis());


    }

    @Test
    public void clientTest1() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        TimeUnit.SECONDS.sleep(2);
        socketChannel.bind(new InetSocketAddress("localhost",8090));

    }
}
