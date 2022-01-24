package thinkingJava.Io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by 15151 on 2019/5/12.
 */
public class TransferTo<T> {
    public void haha(T t) {

    }

    public static <T> void heihei(T t) {

    }

    public static void main(String[] args) {
        try {
            FileChannel in = new FileInputStream("./test.java").getChannel(),
                    out = new FileOutputStream("./transfer.txt").getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
