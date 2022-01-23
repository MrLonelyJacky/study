package thinkingJava.Io.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by 15151 on 2019/5/12.
 */
public class Wrap {
    public static void main(String[] args) {
        //rap只是简单地创建一个具有指向被包装数组的引用的缓冲区，
        // 该数组成为后援数组。w，反之亦然。
        byte[] arr = new byte[100];
        Arrays.fill(arr,(byte)1);
        ByteBuffer buffer = ByteBuffer.wrap(arr,3,25);
        arr[0] = 2;
        buffer.position(0);
        System.out.println(buffer.get());
        System.out.println(Arrays.toString(buffer.array()));
    }
}
