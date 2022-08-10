package netty.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MutiplexerTimeServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定监听端口
     *
     * @param port
     */
    public MutiplexerTimeServer(int port) {
        try {
            selector = Selector.open();

            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);  // 设置为异步阻塞模式
            servChannel.socket().bind(new InetSocketAddress(port), 1024);// 设置 backlog =1024  ， requested maximum length of the queue of incoming connections.
            servChannel.register(selector, SelectionKey.OP_ACCEPT); // 设置 操作位为 ACCEPT
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                //注册客户端的channel
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();    // 类型为  ACCEPT 建立 连接（相当于TCP 3 次握手），
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ); // add the new connection to the selector
            }
            if (key.isReadable()) {
                // read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024); // 1MB 的缓冲区
                int readBytes = sc.read(readBuffer); // 读取请求流
                if (readBytes > 0) {

                }
            }
        }
    }


    public void stop() {
        this.stop = true;
    }
}
