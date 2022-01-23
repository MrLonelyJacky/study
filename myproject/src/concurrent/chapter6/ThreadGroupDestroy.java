package concurrent.chapter6;

/**
 * Created by 15151 on 2020/4/28.
 * destroy 将该group从父级中移除 只针对没有active线程的group
 */
public class ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        System.out.println("group is destroyed?"+group.isDestroyed());
        main.list();
        group.destroy();
        System.out.println("group is destroyed?"+group.isDestroyed());
        main.list();
    }
}
