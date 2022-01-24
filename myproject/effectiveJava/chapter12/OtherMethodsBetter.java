package effectiveJava.chapter12;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 15151 on 2020/3/20.
 *  反序列化bomb 原因是因为hashSet 反序列化时他的元素是set 这样一百层的set 大概会执行2的100次方
 *  导致拒绝服务 Denial of service
 * we do not recommend serialize    json is a good choice
 * if u have no choice but to use serialize mind do not deserialize any data.txt u don not trust
 * like RMI    if u have no choice the java 9 provide  object deserialization filtering
 */
public class OtherMethodsBetter {
    public static void bomb() throws IOException, ClassNotFoundException {
        Set<Object> root = new HashSet<>();
        Set<Object> s2 = new HashSet<>();
        Set<Object> s1 = root;
        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();
            t1.add("foo");
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
        String filePath="E:\\a.txt";
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(root);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //deSerialize bomb!!!!!hahaha
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        Set object = (Set) objectInputStream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       bomb();
    }
}
