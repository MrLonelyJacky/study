package netty.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.chat.message.ChatRequestMessage;
import netty.chat.message.ChatResponseMessage;
import netty.chat.message.LoginRequestMessage;
import netty.chat.message.LoginResponseMessage;
import netty.chat.server.service.UserServiceFactory;
import netty.chat.server.session.SessionFactory;

/**
 * 聊天发消息handler
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage chatRequestMessage) throws Exception {
        System.out.println("==============chat handler==============");
        //发送对象
        String to = chatRequestMessage.getTo();
        //获取发送对象所在channel
        Channel channel = SessionFactory.getSession().getChannel(to);
        if (channel!=null){
            //在线
            channel.writeAndFlush(new ChatResponseMessage(chatRequestMessage.getFrom(),chatRequestMessage.getContent()));
        }else {
            //不在线 向发送者返回消息
            ctx.writeAndFlush(new ChatResponseMessage(false,"对方不在，无法接收"));
        }
    }
}
