package effectiveJava.chapter2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/12/24.
 */
public class TryWithResource {
    static String firstLineOfFile(String path) throws IOException {

        /*try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }*/
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        } finally {
            bufferedReader.close();
        }
    }

    public static void main(String[] args) throws IOException {
        //这是一个损坏的文件
        String s = firstLineOfFile("D:/%E5%8A%A8%E4%BD%9C%E5%88%86%E7%B1%BB%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF.xlsx");
        System.out.println(s);
    }
}
