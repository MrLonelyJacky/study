package concurrent.chapter2;

/**
 * Created by 15151 on 2020/3/27.
 *
 * @author vinci
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("testGroup");
        Thread t2 = new Thread(group, "t2");
        System.out.println(t1.getThreadGroup().getName());
        System.out.println(t2.getThreadGroup().getName());
    }
}
