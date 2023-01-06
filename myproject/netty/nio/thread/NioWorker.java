package netty.nio.thread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: work线程处理读写
 * @author: jacky
 * @create: 2023-01-06 09:27
 **/
public class NioWorker implements Runnable{
    private Thread thread;
    private Selector selector;
    private final String name;
    private boolean start;

    private ConcurrentLinkedQueue<Runnable> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();


    public NioWorker(String name) {
        this.name = name;
    }

    public void register(SocketChannel channel){
        if (!start){
            thread = new Thread(this, name);
            try {
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            thread.start();
            start = true;
        }
        concurrentLinkedQueue.add(()->{
            //客户端也要注册到selector中由selector管理 可读事件
            try {
                channel.register(selector, SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        });
        //wakeup是想更快一步注册read事件
        selector.wakeup();

    }

    @Override
    public void run() {
        while (true){
            try {
                selector.select();
                Runnable poll = concurrentLinkedQueue.poll();
                if (poll!=null){
                    poll.run();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if (next.isReadable()){
                    //如果是可读事件，则拿到对应的客户端发送的数据 读取
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(16);
                    try {
                        socketChannel.read(allocate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    allocate.flip();
                    debugBuffer(allocate);
                    allocate.clear();
                }
            }

        }
    }


    public Selector getSelector() {
        return selector;
    }

    private void debugBuffer(ByteBuffer allocate) {

    }
}
