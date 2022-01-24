package thinkingJava.Io;

import java.io.File;

/**
 * Created by 15151 on 2019/2/25.
 */
public class FileDemo {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0){
            list = path.list();
        }else {

        }
    }
}
