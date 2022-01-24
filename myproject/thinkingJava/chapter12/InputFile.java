package thinkingJava.chapter12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 15151 on 2020/3/19.
 * for fileNotFoundException we do not need close file
 * because it has not opened yet
 * rethrow the exception because we do not mistake others 'it is Initialized and can be used'
 */
public class InputFile {
    private BufferedReader in;

    public InputFile(String fileName) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fileName));
            //other code in there may throw exception
        } catch (FileNotFoundException e) {
            System.out.println("could not open file" + fileName);
            //was not open so do not close it
            throw e;
        }catch (Exception e){
            //all other exception must close it
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("close fail");
            }
            //rethrow
            throw e;
        }finally {
            //do things to handle other exception
            //but not close bufferInput in there
        }
    }

    /**
     * we try the exception and translate into a new exception
     * it is called exception tanslation
     * in the exception design process we always want to know
     * 抛出异常在当前层处理呢 还是直接继续抛出 还是转译成其他异常再抛出
     * 这里转译成非受检异常表示编程错误
     * @return
     */
    public String getLine(){
        String s;
        try {
            s =in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("readLine failed");
        }
        return s;
    }

    public void dispose(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("in.close fail");
        }
    }
}
