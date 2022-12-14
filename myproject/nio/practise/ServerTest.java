package nio.practise;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: jacky
 * @create: 2022-12-13 09:39
 **/
public class ServerTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8090));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector open = Selector.open();
        serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);
        while (true) {
            open.select();
            Set<SelectionKey> selectionKeys = open.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                //selector才会从selectorkey集合中删除，如果不删除下次有新事件过来的时候，该key还存在集合中
                iterator.remove();
                if (next.isAcceptable()) {
                    System.out.println("开始链接。。。。。。");
                    SocketChannel accept = serverSocketChannel.accept();
                    //设置非阻塞，基于事件的方式
                    accept.configureBlocking(false);
                    //客户端注册事件
                    accept.register(open, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    System.out.println("读取客户端数据");
                    try {
                        SocketChannel socketChannel = (SocketChannel) next.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(16);
                        int read = socketChannel.read(allocate);
                        if (read == -1) {
                            //正常关闭
                            next.cancel();
                        }
                        allocate.flip();
                        //todo print the allocate
                    } catch (IOException e) {
                        //客户端 不正常关闭
                        e.printStackTrace();
                        //cancel  客户端关闭时会触发一个read事件 就会一直循环告诉你有事件要read处理  cancel会反注册，真正从selector中删除key
                        next.cancel();
                    }
                }
            }


        }
    }



}
