package netty.pack.tlc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * tlc解码器  粘包半包解决方案之一
 * 长度
 *
 */
public class TlcTest {
    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024,0,4,0,4)
                ,new LoggingHandler(LogLevel.INFO)
        );
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        tlcData("hello,world",buffer);
        tlcData("heiheihei",buffer);
        embeddedChannel.writeInbound(buffer);


    }

    private static void tlcData(String content, ByteBuf buffer) {

        //写入长度和内容
        buffer.writeInt(content.length());
        buffer.writeBytes(content.getBytes());

    }
}
