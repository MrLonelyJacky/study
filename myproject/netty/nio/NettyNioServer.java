package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 非阻塞模式 cpu空转
 */
public class NettyNioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.configureBlocking(false);
        open.bind(new InetSocketAddress("127.0.0.1",8080));

        List<SocketChannel> socketChannelList = new ArrayList<>();
        ByteBuffer allocate = ByteBuffer.allocate(16);
        while (true){
            //此处是非阻塞的 accept接收客户端的连接
            SocketChannel accept = open.accept();
            if (accept!=null){
                //socket也设置为非阻塞
                accept.configureBlocking(false);
                socketChannelList.add(accept);
            }
            for (SocketChannel socketChannel : socketChannelList){
                //接收客户端发送的数据 非阻塞了
                int read = socketChannel.read(allocate);
                if (read>0){
                    //读到数据
                    //切换读模式
                    allocate.flip();
                    debugBuffer(allocate);
                    //清空buffer
                    allocate.clear();
                }
            }
        }
    }

    private static void debugBuffer(ByteBuffer allocate) {

    }
}
