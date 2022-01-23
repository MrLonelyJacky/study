package concurrent.chapter1;

/**
 * Created by 15151 on 2020/3/27.
 * the stack size more higher the recursion depth is more higher
 * the stack size more lower the jvm can create more threads
 */
public class ThreadConstruction {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("testGroup");
        Runnable runnable = new Runnable() {
            static final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                recurse(0);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurse(i + 1);
                }
            }
        };
        Thread thread = new Thread(threadGroup,runnable,"test",1);
        thread.start();
    }
}
