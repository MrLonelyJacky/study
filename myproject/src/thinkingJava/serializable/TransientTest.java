package thinkingJava.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by 15151 on 2018/11/22.
 */
public class TransientTest {
    public static void main(String[] args)
    {
        LabeledPoint label = new LabeledPoint("Book", 5.00, 5.00);
        try
        {
            System.out.println(label); // 写入前
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("thinkingJava.Label.txt"));
            out.writeObject(label);    //通过对象输出流，将label写入流中
            out.close();
            System.out.println(label);// 写入后
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("thinkingJava.Label.txt"));
            LabeledPoint label1 = (LabeledPoint) in.readObject();
            in.close();
            System.out.println(label1);// 读出并加1.0后
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
