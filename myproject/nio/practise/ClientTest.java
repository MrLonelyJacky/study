package nio.practise;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: jacky
 * @create: 2022-12-13 10:56
 **/
public class ClientTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress(8090));
        TimeUnit.SECONDS.sleep(5);
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.put("abcdd".getBytes());
        open.write(allocate);

    }
}
