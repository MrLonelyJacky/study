package netty.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
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
            servChannel.register(selector, SelectionKey.OP_ACCEPT); // 设置 操作位为 ACCEPT 服务端就绪事件
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
                    handleInput(next);
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
                int readBytes = sc.read(readBuffer); // 读取请求流 写入buffer中
                if (readBytes > 0) {
                    //切换读模式  position（读写操作位置）此时为0了，limit为之前的position的位置(所有读写操作都是在工作区position--->)
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body =new String(bytes,"utf-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    //向客户端发送数据
                    doWrite(sc,currentTime);
                }else if (readBytes == 0){
                    //没有数据
                }else {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }
            }
        }
    }


    public void stop() {
        this.stop = true;
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();//每次读写完数据后都要flip一把
            sc.write(writeBuffer);
        }
    }
}
