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

public class RpcClient {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);
        //自定义的数据协议
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        RpcResponseMessageHandler rpcResponseMessageHandler = new RpcResponseMessageHandler();
        try {
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
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8088).sync();
            Channel channel = sync.channel();
            channel.writeAndFlush(new RpcRequestMessage(1, "netty.chat.server.service.HelloService","sayHello"
                    , String.class, new Class[]{String.class}, new Object[]{"张三"}))
                    .addListener(promise->{
                        if (!promise.isSuccess()){
                            Throwable cause = promise.cause();
                            System.out.println(cause);
                        }
                    });
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }
}
