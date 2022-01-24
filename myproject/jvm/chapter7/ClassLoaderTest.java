package jvm.chapter7;

import sun.misc.Launcher;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;


/**
 * Created by 15151 on 2020/5/21.
 * 虚拟机的类加载器和自己定义的类加载器不一致，导致输出false
 * 这是因为Java虚拟机中同时存在了两个ClassLoaderTest类，一个是由虚拟
 * 机的应用程序类加载器所加载的，另外一个是由我们自定义的类加载器加载的，虽然它们都来自同一
 * 个Class文件，但在Java虚拟机中仍然是两个互相独立的类，做对象所属类型检查时的结果自然为
 * false
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };
        java.lang.Object o = myLoader.loadClass("jvm.chapter7.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof ClassLoaderTest);
        System.out.println(System.getProperty("java.class.path"));

    }
}
