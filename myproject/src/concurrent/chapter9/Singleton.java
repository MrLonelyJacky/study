package concurrent.chapter9;

/**
 * Created by 15151 on 2020/4/29.
 * 如果将注释2的代码移动到注释1会有不同的结果
 * 类的加在阶段：简而言之1、将class文件中的二进制数据读取到内存中
 * 2、然后将该字节流所代表的静态存储结构转换为方法区中运行时的数据结构
 * 3、并且在堆内存中生成一个该类的java.lang.Class对象 作为方法区这个类的格中数据访问接口
 * 
 */
public class Singleton {

    //1
    private static int x = 0;
    private static int y;
    //2
    private static Singleton instance = new Singleton();

    public Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton =Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
