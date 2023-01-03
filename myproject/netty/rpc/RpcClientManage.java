package netty.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.chat.protocal.MessageCodecSharable;
import netty.chat.protocal.ProcotolFrameDecoder;
import netty.rpc.handler.RpcResponseMessageHandler;
import netty.rpc.message.RpcRequestMessage;

/**
 * @description:
 * @author: jacky
 * @create: 2023-01-03 09:30
 **/
public class RpcClientManage {
    private static Channel channel;

    private static final Object lock = new Object();

    /**
     * double check
     * @return
     */
    public static Channel getChannel() {
        if (channel != null) {
            return channel;
        }
        synchronized (lock) {
            if (channel != null) {
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    private static void initChannel() {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);
        //自定义的数据协议
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        RpcResponseMessageHandler rpcResponseMessageHandler = new RpcResponseMessageHandler();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        //解决粘包半包问题
                        socketChannel.pipeline().addLast(new ProcotolFrameDecoder());
                        socketChannel.pipeline().addLast(loggingHandler);
                        socketChannel.pipeline().addLast(messageCodecSharable);
                        socketChannel.pipeline().addLast(rpcResponseMessageHandler);
                    }
                });
        try {
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8088).sync();
            channel = sync.channel();

            channel.closeFuture().addListener(future -> nioEventLoopGroup.shutdownGracefully());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        channel.writeAndFlush(new RpcRequestMessage(1, "netty.chat.server.service.HelloService", "sayHello"
                , String.class, new Class[]{String.class}, new Object[]{"张三"}))
                .addListener(promise -> {
                    if (!promise.isSuccess()) {
                        Throwable cause = promise.cause();
                        System.out.println(cause);
                    }
                });
    }

}
