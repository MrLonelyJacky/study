package netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.chat.handler.ChatRequestMessageHandler;
import netty.chat.handler.LoginRequestHandler;
import netty.chat.message.LoginRequestMessage;
import netty.chat.message.LoginResponseMessage;
import netty.chat.protocal.MessageCodecSharable;
import netty.chat.protocal.ProcotolFrameDecoder;
import netty.chat.server.service.UserServiceFactory;

/**
 * 服务端
 */
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);
        //自定义的数据协议
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        LoginRequestHandler loginRequestHandler = new LoginRequestHandler();
        ChatRequestMessageHandler chatRequestMessageHandler = new ChatRequestMessageHandler();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss,work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new ProcotolFrameDecoder());
                            nioSocketChannel.pipeline().addLast(loggingHandler);
                            nioSocketChannel.pipeline().addLast(messageCodecSharable);
                            nioSocketChannel.pipeline().addLast(loginRequestHandler);
                            nioSocketChannel.pipeline().addLast(chatRequestMessageHandler);
                        }
                    });
            ChannelFuture sync = serverBootstrap.bind(8088).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
