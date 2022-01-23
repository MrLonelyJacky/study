import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 15151 on 2018/11/20.
 */
public class NioTest {
    public static void main(String[] args) {
        copyFileNio("D:\\a.txt","D:\\ac.txt");
    }

    /**
     * 拷贝文件
     */
    public static void copyFileNio(String src, String dst) {
        FileInputStream fi=null;
        FileOutputStream fo=null;
        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
             fi = new FileInputStream(src);
             fo = new FileOutputStream(dst);
             inChannel = fi.getChannel();
             outChannel = fo.getChannel();
            //获取buffer容器
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int i = inChannel.read(buffer);
                if (i == -1) {
                    break;
                }
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inChannel!=null){
                    inChannel.close();
                }
                if (outChannel!=null){
                    outChannel.close();
                }
                if (fi!=null){
                    fi.close();
                }
                if (fo!=null){
                    fo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
