package netty.rpc.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.chat.server.service.HelloService;
import netty.chat.server.service.ServicesFactory;
import netty.rpc.message.RpcRequestMessage;
import netty.rpc.message.RpcResponseMessage;

import java.lang.reflect.Method;

@ChannelHandler.Sharable
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage rpcRequestMessage) throws Exception {
        System.out.println("============rpc开始输出"+rpcRequestMessage+"============");

    }
}
