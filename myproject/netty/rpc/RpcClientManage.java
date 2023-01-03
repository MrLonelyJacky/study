package netty.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import netty.chat.protocal.MessageCodecSharable;
import netty.chat.protocal.ProcotolFrameDecoder;
import netty.chat.protocal.SequenceIdGenerator;
import netty.chat.server.service.HelloService;
import netty.rpc.handler.RpcResponseMessageHandler;
import netty.rpc.message.RpcRequestMessage;

import java.lang.reflect.Proxy;

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
     *
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


    public static <T> void getProxyService(Class<T> serviceClass) {
        HelloService helloService = null;
        helloService.sayHello("adad");
        helloService.sayHello("121212");
        Proxy.newProxyInstance(serviceClass.getClassLoader(), serviceClass.getInterfaces(), ((proxy, method, args) -> {
            RpcRequestMessage rpcRequestMessage = new RpcRequestMessage(SequenceIdGenerator.nextId(), serviceClass.getName(), method.getName()
                    , method.getReturnType(), method.getParameterTypes(), args);
            Channel channel = getChannel();
            channel.writeAndFlush(rpcRequestMessage);
            DefaultPromise<Object> objectDefaultPromise = new DefaultPromise<>(channel.eventLoop());
            RpcResponseMessageHandler.promiseMap.put(rpcRequestMessage.getSequenceId(),objectDefaultPromise);
            Promise<Object> await = objectDefaultPromise.await();
            if (await.isSuccess()){
                return await.getNow();
            }else {
                throw new RuntimeException(await.cause());
            }

        }));
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
