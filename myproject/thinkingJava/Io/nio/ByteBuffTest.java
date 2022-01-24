package thinkingJava.Io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 15151 on 2019/5/10.
 */
public class ByteBuffTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("test.txt");
        OutputStream outputStream = new FileOutputStream("stream.txt");
        int len = -1;
        byte[] bytes = new byte[100];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        test();
    }

    public static void test() {
        ByteBuffer buffer = ByteBuffer.allocate(120);
        FileChannel fin = null;
        FileChannel fout = null;
        try {
            fin = new FileInputStream("test.txt").getChannel();
            fout = new FileOutputStream("fileout.txt").getChannel();
            while (fin.read(buffer) != -1) {
                buffer.flip();
                fout.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
