package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * io多路复用 由selector管理通道
 * 有事件才继续
 */
public class IoDuplicateNioClient {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("127.0.0.1",8080));
        //创造selector管理多个通道
        Selector selector = Selector.open();
        //将服务端通道注册上去，并监听连接事件
        //selectionKey的作用是 当事件发生后会知道是哪个事件和哪个通道
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //select当没有事件时阻塞，如果没事件或者事件不处理则不阻塞
            selector.select();
            //selectedKeys 拿到所有可以知道事件和其对应通道的SelectionKey
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                // 要删除，否则下次再处理这个key时，会因为没有事件报空指针
                iterator.remove();
                if (next.isAcceptable()){
                    //是连接事件 ，找到通道  这里其实就是上面的ssc
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    //客户端也要注册到selector中由selector管理 可读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (next.isReadable()){
                    //如果是可读事件，则拿到对应的客户端发送的数据 读取
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(16);
                    socketChannel.read(allocate);
                    allocate.flip();
                    debugBuffer(allocate);
                    allocate.clear();
                }
            }
        }
    }

    private static void debugBuffer(ByteBuffer allocate) {

    }
}