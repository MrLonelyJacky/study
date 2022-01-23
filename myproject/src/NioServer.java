

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {
    private int port;
    private Selector selector;
    private ExecutorService service = Executors.newFixedThreadPool(5);

    public NioServer(int port) {
        this.port = port;
    }

    public void init(){
        ServerSocketChannel ssc = null;
        try {
            //开通通道
            ssc = ServerSocketChannel.open();
            //非阻塞方式
            ssc.configureBlocking(false);
            //绑定地址
            ssc.bind(new InetSocketAddress(port));
            selector = Selector.open();
            //注册到selector
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NioServer started ......");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

    public void accept(SelectionKey key){
        //根据key获取channel
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        try {
            SocketChannel accept = ssc.accept();
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
