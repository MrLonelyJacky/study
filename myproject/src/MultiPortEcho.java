import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 15151 on 2018/11/21.
 */
public class MultiPortEcho {
    private int ports[];
    private ByteBuffer echoBuffer= ByteBuffer.allocate(1024);
    public MultiPortEcho(int ports[]) throws IOException {
        this.ports=ports;
        go();
    }
    private void go() throws IOException {
        Selector selector=null;

            //1.创建selector，它是网络nio的核心
             selector=Selector.open();
             //为每个端口打开一个监听，并把这些监听注册到selector中
            for (int i=0;i<ports.length;i++){
                //2.打开一个serverSocketChnnael，每监听一个端口就需要一个channnel
                ServerSocketChannel ssc=ServerSocketChannel.open();
                ssc.configureBlocking(false);//设为非阻塞
                ServerSocket ss=ssc.socket();
                InetSocketAddress address=new InetSocketAddress(ports[i]);
                ss.bind(address);//监听该端口号
                //3.注册到selector
                //第二个参数是监听我们感兴趣的事件,Accept是新建立连接事件
                SelectionKey selectionKey=ssc.register(selector,SelectionKey.OP_ACCEPT);
                System.out.println("going on listening on"+ports[i]);
            }
        //4.开始循环我们已经注册和感兴趣的事件
            while (true){
                //该方法会阻塞，知道有一个注册事件发生
                int num=selector.select();
                //返回所有已经注册的selectionkey
                Set<SelectionKey> selected=selector.selectedKeys();
                //迭代selected依次处理里面的selectionkey
                Iterator<SelectionKey> it = selected.iterator();
                while(it.hasNext()){
                    SelectionKey key=it.next();
                    //5.监听新连接，到这里我们只是注册通道，并且注册连接事件，监听端口
                    //为了确认注册事件是否已经可以接受
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        //6. 接收了一个新连接。因为我们知道这个服务器套接字上有一个传入连接在等待
                        //所以可以安全地接受它；也就是说，不用担心 accept() 操作会阻塞
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        // 7. 讲新连接注册到selector。将新连接的 SocketChannel 配置为非阻塞的
                        //而且由于接受这个连接的目的是为了读取来自套接字的数据，所以我们还必须将 SocketChannel 注册到 Selector上
                        SelectionKey newKey = sc.register(selector,SelectionKey.OP_READ);
                        it.remove();
                        System.out.println("Got connection from " + sc);
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        // Read the data.txt
                        SocketChannel sc = (SocketChannel) key.channel();
                        // Echo data.txt
                        int bytesEchoed = 0;
                        while (true) {
                            echoBuffer.clear();
                            int r = sc.read(echoBuffer);
                            if (r <= 0) {
                                break;
                            }
                            echoBuffer.flip();
                            sc.write(echoBuffer);
                            bytesEchoed += r;
                        }
                        System.out.println("Echoed " + bytesEchoed + " from " + sc);
                        it.remove();
                    }
                }
            }
    }
    public static void main(String[] args) throws IOException {
        String args2[]={"9090","9002","9003"};
        if (args2.length <= 0) {
            System.err.println("Usage: java MultiPortEcho port [port port ...]");
            System.exit(1);
        }
        int ports[] = new int[args2.length];
        for (int i = 0; i < args2.length; ++i) {
            ports[i] = Integer.parseInt(args2[i]);
        }
        new MultiPortEcho(ports);
    }
}
