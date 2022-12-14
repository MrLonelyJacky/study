package nio.practise;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: jacky
 * @create: 2022-12-14 17:50
 **/
public class WorkerTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8090));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector open = Selector.open();
        serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);
        Worker worker = new Worker();
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
                    worker.register(accept);
                    accept.register(open, SelectionKey.OP_READ);
                }
            }


        }
    }


    private static class Worker extends Thread{
        private Thread thread;
        private Selector selector;
        private boolean start;


        public void register(SocketChannel socketChannel)  {
            if (!start){
                thread = new Thread();
                try {
                    selector = Selector.open();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                start = true;
            }

            selector.wakeup();
            try {
                socketChannel.register(selector,SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }

        }

        @Override
        public  void start() {
            while (true){
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        iterator.remove();
                        if (next.isReadable()){
                            System.out.println("read data .......");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
