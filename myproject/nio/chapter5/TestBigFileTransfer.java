package nio.chapter5;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class TestBigFileTransfer {
    @Test
    public void testServer1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8090));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        boolean isRunning = true;
        SocketChannel socketChannel = null;
        while (isRunning == true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if (next.isAcceptable()){
                    socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                }
                if (next.isWritable()){
                    RandomAccessFile randomAccessFile = new RandomAccessFile("D://a.txt","rw");
                    System.out.println("file length="+randomAccessFile.length());
                    FileChannel channel = randomAccessFile.getChannel();
                    channel.transferTo(0,randomAccessFile.length(),socketChannel);
                    channel.close();
                    randomAccessFile.close();
                    socketChannel.close();
                }
            }
        }
        serverSocketChannel.close();

    }

    @Test
    public void testClient1() throws IOException {
        //socketChannel是阻塞模式
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8090));
        Selector open = Selector.open();
        socketChannel.register(open,SelectionKey.OP_CONNECT);
        while (true){
            if (socketChannel.isOpen()){
                open.select();
                Set<SelectionKey> selectionKeys = open.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isConnectable()){
                        while (!socketChannel.finishConnect()){
                            System.out.println("尝试建立连接");
                        }
                        socketChannel.register(open,SelectionKey.OP_READ);
                    }
                    if (next.isReadable()){
                        ByteBuffer allocate = ByteBuffer.allocate(50000);
                        int read = socketChannel.read(allocate);
                        allocate.flip();
                        while (read!=-1){
                            read = socketChannel.read(allocate);
                            allocate.clear();
                        }
                        socketChannel.close();
                    }
                }
            }else {
                break;
            }
        }
    }
}
