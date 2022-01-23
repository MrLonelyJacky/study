package thinkingJava.Io;

import java.io.*;

/**
 * Created by 15151 on 2019/5/10.
 */
public class Redirecting {
    public static void main(String[] args) throws Exception {
        PrintStream console = System.out;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\project\\untitled\\src\\thinkingJava.Io\\Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.java")));
        System.setIn(bis);
        System.setOut(out);
        System.setErr(out);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }

}
