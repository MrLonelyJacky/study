package thinkingJava.Io.xlh;

import java.io.*;

/**
 * Created by 15151 on 2019/5/10.
 */
public class Worm implements Serializable{
    public static void main(String[] args) throws Exception {
        Data data = new Data(2);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.txt.out"));
        outputStream.writeObject("data.txt storage");
        outputStream.writeObject(data);
        outputStream.close();
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.txt.out"));
        System.out.println(inputStream.readObject());

    }
}
