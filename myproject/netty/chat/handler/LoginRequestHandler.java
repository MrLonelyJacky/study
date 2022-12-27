package netty.chat.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.chat.message.LoginRequestMessage;
import netty.chat.message.LoginResponseMessage;
import netty.chat.server.service.UserServiceFactory;
import netty.chat.server.session.SessionFactory;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage loginRequestMessage) throws Exception {
        System.out.println("==============login handler==============");
        String username = loginRequestMessage.getUsername();
        String password = loginRequestMessage.getPassword();
        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage loginResponseMessage;
        if (login){
            //todo 注释
            SessionFactory.getSession().bind(ctx.channel(),username);
            loginResponseMessage = new LoginResponseMessage(true,"登录成功");
        }else {
            loginResponseMessage = new LoginResponseMessage(false,"登录失败");
        }
        ctx.writeAndFlush(loginResponseMessage);
    }
}
