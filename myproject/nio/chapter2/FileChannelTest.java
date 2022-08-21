package nio.chapter2;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws FileNotFoundException {


    }

    @Test
    public void testOne() throws IOException {
        FileOutputStream fosRef = new FileOutputStream(new File("E:\\projectFile\\study\\myproject\\nio\\file\\a.txt"));
        FileChannel fileChannel = fosRef.getChannel();
        ByteBuffer wrap = ByteBuffer.wrap("abcdefghtjkddaddddkjfldqaa".getBytes());
        System.out.println("wrap position:"+wrap.position());
        System.out.println("a fileChannel.position="+fileChannel.position());
        System.out.println("write wrap result="+fileChannel.write(wrap));
        System.out.println("wrap position after write:"+wrap.position());
        System.out.println("b fileChannel.position="+fileChannel.position());
        fileChannel.position(2);
        wrap.rewind();//let position clear zero in order to write again
        //write again
        System.out.println("write2 wrap result="+fileChannel.write(wrap));
        System.out.println("c fileChannel.position="+fileChannel.position());
    }


    @Test
    public void testTwo() throws IOException {
        FileOutputStream fosRef = new FileOutputStream(new File("E:\\projectFile\\study\\myproject\\nio\\file\\a.txt"));
        FileChannel fileChannel = fosRef.getChannel();
        ByteBuffer wrap = ByteBuffer.wrap("abcde".getBytes());
        ByteBuffer wrap2 = ByteBuffer.wrap("12345".getBytes());
        fileChannel.write(wrap);
        wrap2.position(1);
        wrap2.limit(3);
        fileChannel.position(2);
        fileChannel.write (wrap2) ;
    }


    @Test
    public void test3() throws IOException {
        try (
                FileInputStream fis = new FileInputStream("E:\\projectFile\\study\\myproject\\nio\\file\\b.txt");
                FileChannel channel = fis.getChannel()
        ){
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            // 正常读取4字节到buffer
            int result = channel.read(byteBuffer);
            System.out.println("position:"+byteBuffer.position());
            System.out.println("result:"+result);
            //make position to zero read again
            byteBuffer.rewind();
            int read2 = channel.read(byteBuffer);
            System.out.println(read2);
            //read remaining 剩余空间为0 因为读过了一次
            int read = channel.read(byteBuffer);
            System.out.println(read);

            //let buffer clear in order to read again
            byteBuffer.clear();
            int read1 = channel.read(byteBuffer);
            System.out.println(read1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
