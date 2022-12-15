package netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @description:
 * @author: jacky
 * @create: 2022-12-15 11:23
 **/
public class NettyServer {
    public static void main(String[] args) {
        new ServerBootstrap()
                //相当于之前学的 boss线程处理链接 worker线程 处理读写事件
                .group(new NioEventLoopGroup())
                //相当于之前 nio 的ServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //相当于之前处理nio的读写事件
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                }).bind(8090);
    }
}
