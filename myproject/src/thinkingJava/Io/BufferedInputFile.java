package thinkingJava.Io;

import java.io.*;

/**
 * Created by 15151 on 2019/5/10.
 */
public class BufferedInputFile {
    public static String read(String fileName) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s + "\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(read("C:\\project\\untitled\\src\\thinkingJava.Io\\BufferedInputFile.java"));
    }
}
