package jvm.classLoader;

public class ClassLoaderOver extends ClassLoader{


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }
}
