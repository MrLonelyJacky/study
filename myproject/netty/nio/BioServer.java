package netty.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * bio缺点 accept和read都会阻塞
 * 要开线程处理 accept和read都阻塞，大量请求支撑不住
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress("127.0.0.1",8080));
        List<SocketChannel> socketChannelList = new ArrayList<>();
        ByteBuffer allocate = ByteBuffer.allocate(16);
        while (true){
            //此处是阻塞的 accept接收客户端的连接
            SocketChannel accept = open.accept();
            socketChannelList.add(accept);
            for (SocketChannel socketChannel : socketChannelList){
                //接收客户端发送的数据
                int read = socketChannel.read(allocate);
                //切换读模式
                allocate.flip();
                debugBuffer(allocate);
                //清空buffer
                allocate.clear();
            }
        }
    }

    private static void debugBuffer(ByteBuffer allocate) {

    }
}
