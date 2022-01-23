package concurrent.chapter14;

/**
 * Created by 15151 on 2020/5/12.
 * 饿汉式 初始化会导致byte被初始化
 */
public final class Singleton {
    private byte[] data = new byte[1024];
    private static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
