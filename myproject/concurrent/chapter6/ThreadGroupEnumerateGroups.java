package concurrent.chapter6;

/**
 * Created by vinci on 2020/4/27.
 * 复制threadGroup
 */
public class ThreadGroupEnumerateGroups {
    public static void main(String[] args) {
        ThreadGroup group1 = new ThreadGroup("MyGroup1");
        ThreadGroup group2 = new ThreadGroup(group1,"MyGroup1");
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup[] list = new ThreadGroup[main.activeGroupCount()];
        int enumerate = main.enumerate(list);
        System.out.println(enumerate);
        enumerate = main.enumerate(list,false);
        System.out.println(enumerate);
    }
}
