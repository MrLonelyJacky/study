package nio.chapter6;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: jacky
 * @create: 2022-09-13 10:27
 **/
public class AsyncChannelTest {
    @Test
    public void testServer1() throws IOException, InterruptedException {
        AsynchronousServerSocketChannel open = AsynchronousServerSocketChannel.open();
        open.bind(new InetSocketAddress(8088));
        open.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @SneakyThrows
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                open.accept(null, this);
                System.out.println("complete name" + Thread.currentThread().getName());
                ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                Future<Integer> read = result.read(byteBuffer);
                System.out.println(new String(byteBuffer.array(), 0, read.get()));
                //处理连接
                handle(result);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed");
            }
        });
        TimeUnit.SECONDS.sleep(12);
    }

    private void handle(AsynchronousSocketChannel result) throws IOException {
        System.out.println("handle connect" + result.getRemoteAddress());
    }

    @Test
    public void testClient1() throws IOException {
        Socket socket = new Socket("localhost", 8088);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我来自客户端".getBytes());
        outputStream.flush();
        socket.close();
    }

    @Test
    public void testClient1_1() throws IOException, InterruptedException {
        AsynchronousSocketChannel open = AsynchronousSocketChannel.open();
        open.connect(new InetSocketAddress("localhost", 8088), "aass", new CompletionHandler<Void, String>() {
            @Override
            public void completed(Void result, String attachment) {
                open.write(ByteBuffer.wrap("我来自客户端1_1".getBytes()));
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 测试异步读
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testServer2Read() throws IOException, InterruptedException {
        AsynchronousServerSocketChannel open = AsynchronousServerSocketChannel.open();
        open.bind(new InetSocketAddress(8088));
        open.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @SneakyThrows
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                open.accept(null, this);
                System.out.println("complete name" + Thread.currentThread().getName());
                ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                channel.read(byteBuffer, 10, TimeUnit.SECONDS, null,
                        new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer result, Object attachment) {
                                if (result == -1) {
                                    System.out.println("没有传输数据就关了~");
                                }
                                if (result == byteBuffer.limit()) {
                                    System.out.println("服务端获取客户端完整数据");
                                }

                                try {
                                    channel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {

                            }
                        });

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed");
            }
        });
        TimeUnit.SECONDS.sleep(12);
    }

    @Test
    public void testClient2_1() throws IOException, InterruptedException {
        AsynchronousSocketChannel open = AsynchronousSocketChannel.open();
        open.connect(new InetSocketAddress("localhost", 8088), "aass", new CompletionHandler<Void, String>() {
            @Override
            public void completed(Void result, String attachment) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.MAX_VALUE / 1000);
                for (int i = 0; i < Integer.MAX_VALUE / 1000 - 3; i++) {
                    byteBuffer.put("1".getBytes());
                }
                byteBuffer.put("end".getBytes());
                byteBuffer.flip();
                int writeSum = 0;
                try {
                    while (writeSum < byteBuffer.limit()) {
                        Future<Integer> write = open.write(byteBuffer);
                        Integer writeLength = write.get();
                        writeSum = writeSum+writeLength;
                    }
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });
        TimeUnit.SECONDS.sleep(5);
    }
}
