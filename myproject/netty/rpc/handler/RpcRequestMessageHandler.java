package netty.rpc.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.chat.message.LoginRequestMessage;
import netty.chat.message.LoginResponseMessage;
import netty.chat.server.service.HelloService;
import netty.chat.server.service.ServicesFactory;
import netty.chat.server.service.UserServiceFactory;
import netty.chat.server.session.SessionFactory;
import netty.rpc.message.RpcRequestMessage;
import netty.rpc.message.RpcResponseMessage;

import java.lang.reflect.Method;

@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage rpcRequestMessage) throws Exception {
        System.out.println("==============开始rpc调用=============");
        RpcResponseMessage rpcResponseMessage = new RpcResponseMessage();
        try {
            HelloService service = (HelloService) ServicesFactory.getService(Class.forName(rpcRequestMessage.getInterfaceName()));
            Method method = service.getClass().getMethod(rpcRequestMessage.getMethodName(), rpcRequestMessage.getParameterTypes());
            Object invoke = method.invoke(service, rpcRequestMessage.getParameterValue());
            rpcResponseMessage.setReturnValue(invoke);
        }catch (Exception e){
            e.printStackTrace();
            rpcResponseMessage.setExceptionValue(e);
        }
        ctx.writeAndFlush(rpcResponseMessage);

    }
}
