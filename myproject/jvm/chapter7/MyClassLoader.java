package jvm.chapter7;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //the first test two different classLoader
        MyClassLoader myClassLoader = new MyClassLoader();
        MyClassLoader myClassLoader2 = new MyClassLoader();
        //result false
        System.out.println(myClassLoader == myClassLoader2);

        Class<?> loadClass = myClassLoader.loadClass("E:\\projectFile\\myproject\\src\\jvm\\chapter7\\SubClass.java");
        Class<?> loadClass2 = myClassLoader2.loadClass("E:\\projectFile\\myproject\\src\\jvm\\chapter7\\SubClass.java");
        //result true
        System.out.println(loadClass==loadClass2);

    }
}
