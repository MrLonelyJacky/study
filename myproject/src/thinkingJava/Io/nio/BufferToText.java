package thinkingJava.Io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by 15151 on 2019/5/13.
 */
public class BufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("some text".getBytes()));
        fileChannel.close();
        fileChannel = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        //乱码
        System.out.println(buffer.asCharBuffer());
        buffer.rewind();
        String enconding = System.getProperty("file.encoding");
        //对buffer进行编码
        System.out.println("use encoding" + enconding + Charset.forName(enconding).decode(buffer));
        fileChannel = new FileOutputStream("data2.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("some text ".getBytes("GBK")));
        fileChannel.close();
    }
}
