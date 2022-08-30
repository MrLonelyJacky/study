package nio.chapter5;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestSelector {
    @Test
    public void testServer1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8090));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    @Test
    public void testServer2() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8090));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        Selector selector2 = Selector.open();
        SelectionKey register2 =serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey register = serverSocketChannel.register(selector2, SelectionKey.OP_ACCEPT);
        System.out.println(register2 == register);

    }

    @Test
    public void testServer3() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8090));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey register2 =serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(register2 == register);

    }



    @Test
    public void testClient1() throws IOException {
        //socketChannel是阻塞模式
        SocketChannel socketChannel = SocketChannel.open();
        long start = System.currentTimeMillis();
        socketChannel.connect(new InetSocketAddress("localhost",8090));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }


}
