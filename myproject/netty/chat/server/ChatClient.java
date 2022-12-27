package netty.chat.server;

import concurrent.chapter23.CountDownLatch;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.chat.message.*;
import netty.chat.protocal.MessageCodecSharable;
import netty.chat.protocal.ProcotolFrameDecoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);
        //自定义的数据协议
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        try {
            Bootstrap bootstrap = new Bootstrap();
            Scanner scanner = new Scanner(System.in);
            bootstrap.group(nioEventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                            //解决粘包半包问题
                            socketChannel.pipeline().addLast(new ProcotolFrameDecoder());
                            socketChannel.pipeline().addLast(loggingHandler);
                            socketChannel.pipeline().addLast(messageCodecSharable);
                            //我们先要做一个登录功能
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    //开启一个线程 负责接收用户在控制台的输入然后发消息
                                    new Thread(() -> {
                                        System.out.println("请输入用户名:");
                                        String username = scanner.nextLine();

                                        System.out.println("请输入密码:");
                                        String password = scanner.nextLine();
                                        //构造消息对象
                                        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                                        //向服务端写消息，这会触发出栈操作  出战处理器，协议解析该消息
                                        ctx.writeAndFlush(loginRequestMessage);

                                        System.out.println("等待进一步输入");
                                        try {
                                            countDownLatch.await();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (!atomicBoolean.get()) {
                                            //登录失败
                                            ctx.channel().close();
                                            return;
                                        }
                                        while (true) {
                                            System.out.println("==================================");
                                            System.out.println("send [username] [content]");
                                            System.out.println("gsend [group name] [content]");
                                            System.out.println("gcreate [group name] [m1,m2,m3...]");
                                            System.out.println("gmembers [group name]");
                                            System.out.println("gjoin [group name]");
                                            System.out.println("gquit [group name]");
                                            System.out.println("quit");
                                            System.out.println("==================================");
                                            String command = null;
                                            command = scanner.nextLine();

                                            String[] s = command.split(" ");
                                            switch (s[0]) {
                                                case "send":
                                                    ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
                                                    break;
                                                case "gsend":
                                                    ctx.writeAndFlush(new GroupChatRequestMessage(username, s[1], s[2]));
                                                    break;
                                                case "gcreate":
                                                    Set<String> set = new HashSet<>(Arrays.asList(s[2].split(",")));
                                                    set.add(username); // 加入自己
                                                    ctx.writeAndFlush(new GroupCreateRequestMessage(s[1], set));
                                                    break;
                                                case "gmembers":
                                                    ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
                                                    break;
                                                case "gjoin":
                                                    ctx.writeAndFlush(new GroupJoinRequestMessage(username, s[1]));
                                                    break;
                                                case "gquit":
                                                    ctx.writeAndFlush(new GroupQuitRequestMessage(username, s[1]));
                                                    break;
                                                case "quit":
                                                    ctx.channel().close();
                                                    return;
                                            }
                                        }
                                    }, "system in").start();
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    if (msg instanceof LoginResponseMessage) {
                                        //处理登录结果
                                        LoginResponseMessage loginResponseMessage = (LoginResponseMessage) msg;
                                        boolean success = loginResponseMessage.isSuccess();
                                        atomicBoolean.set(success);
                                        countDownLatch.countDown();
                                    }
                                    System.out.println(msg);
                                }
                            });
                        }


                    });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8088).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }
}
