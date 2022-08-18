package netty.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;
    private String name;

    public NioClientHandler(String host, int port,String name) {
        this.host = host;
        this.port = port;
        this.name = name;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(-1);
        }
    }

    @Override
    public void run() {
        try {
            doConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println(this.name+"可用通道："+selectionKeys.size());
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    handleInput(next);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) { // 判断是否连接成功
            SocketChannel channel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                System.out.println(this.name+"尝试连接！");
                //链接成功发送消息
                if (channel.finishConnect()) {
                    System.out.println(this.name+"连接成功！开始注册读就绪和发送数据");
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel);
                } else {
                    System.exit(1);// 连接失败。
                }
            }

            if (key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = channel.read(readBuffer);

                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];

                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now read data is : " + body);
                    //this.stop = true;
                } else if (readBytes < 0) {
                    key.cancel();// 关闭链路
                    channel.close();
                } else {
                    // do nothing
                }
            }
        }
    }

    private void doConnection() throws IOException {
        // 如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            System.out.println(this.name+"链接成功，开始注册多路复用器读就绪！");
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            System.out.println(this.name+"链接失败，开始注册多路复用器连接就绪！");
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 发送数据
     *
     * @param socketChannel
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] req = "QUERY TIME ORDER".getBytes();
        byteBuffer.put(req);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed.");
        }
    }
}
