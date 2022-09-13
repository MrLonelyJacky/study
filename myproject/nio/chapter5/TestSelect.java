package nio.chapter5;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSelect {
    @Test
    public void test1() throws IOException {
        ServerSocketChannel channel1 = ServerSocketChannel.open();
        channel1.configureBlocking(false);
        channel1.bind(new InetSocketAddress("localhost", 8088));
        Selector selector = Selector.open();
        channel1.register(selector, SelectionKey.OP_ACCEPT);
        boolean isRun = true;
        while (isRun) {
            selector.select();
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator = set.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = channel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }
                if (key.isWritable()) {
                    long start = System.currentTimeMillis();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    FileInputStream file = new FileInputStream("E:\\centOs\\CentOS 7 64 位-s001.vmdk");
                    FileChannel fileChannel = file.getChannel();
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(524288000);
                    // 500MB空间
                    while (fileChannel.position() < fileChannel.size()) {
                        fileChannel.read(byteBuffer);
                        byteBuffer.flip();

                        while (byteBuffer.hasRemaining()) {
                            socketChannel.write(byteBuffer);
                        }
                        byteBuffer.clear();
                        System.out.println(fileChannel.position() + " " + fileChannel.size());
                    }
                    long l = System.currentTimeMillis();
                    System.out.println("结束写操作，耗时"+(l-start));
                    socketChannel.close();
                }
            }
        }
        channel1.close();

    }

    @Test
    public void test2() throws IOException {
        SocketChannel channel1 = SocketChannel.open();
        channel1.configureBlocking(false);
        channel1.connect(new InetSocketAddress("localhost", 8088));
        Selector selector = Selector.open();
        channel1.register(selector, SelectionKey.OP_CONNECT);
        boolean isRun = true;
        while (isRun) {
            System.out.println("begin selector");
            if (channel1.isOpen()) {
                selector.select();
                System.out.println("  end selector");
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isConnectable()) {
                        while (! channel1.finishConnect()) {
                        }
                        channel1.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(50000);
                        int readLength = channel1.read(byteBuffer);
                        byteBuffer.flip();
                        long count = 0;
                        while (readLength != -1) {
                            count = count + readLength;
                            System.out.println("count=" + count + " readLength=" +
                                    readLength);
                            readLength = channel1.read(byteBuffer);
                            byteBuffer.clear();
                        }
                        System.out.println("读取结束");
                        channel1.close();

                    }
                }
            } else {
                break;
            }
        }
    }



    @Test
    public void test10() throws IOException, InterruptedException {
        ServerSocketChannel channel1 = ServerSocketChannel.open();
        channel1.configureBlocking(false);
        channel1.bind(new InetSocketAddress("localhost", 8088));
        ServerSocketChannel channel2 = ServerSocketChannel.open();
        channel2.configureBlocking(false);
        channel2.bind(new InetSocketAddress("localhost", 8090));

        Selector selector = Selector.open();
        SelectionKey register = channel1.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey register2 = channel2.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(register==register2);
        boolean isRun = true;
        while (isRun) {
            int select = selector.select();
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("select:"+select+"keys"+keys.size()+"selectorKeys"+selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                ServerSocketChannel nextServer = (ServerSocketChannel) next.channel();

                if (next.isAcceptable()) {
                    SocketChannel accept = nextServer.accept();
                    if (accept == null) {
                        System.out.println("重复消费了哥");
                    }
                    TimeUnit.SECONDS.sleep(3);
                    InetSocketAddress localAddress = (InetSocketAddress) nextServer.getLocalAddress();
                    System.out.println(localAddress.getPort() + "连接了客户端");

                }
                iterator.remove();
            }
            /*channel1.close();
            channel2.close();*/
        }
    }

    /**
     * KQueueSelectorProvider
     */
    @Test
    public void test11() throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress("localhost", 8088));
        socket.close();
    }

    @Test
    public void test12() throws IOException {
        Socket socket = new Socket("localhost",8090);
        socket.close();
    }
}
