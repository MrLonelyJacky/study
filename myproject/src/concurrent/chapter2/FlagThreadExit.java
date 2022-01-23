package concurrent.chapter2;

/**
 * Created by 15151 on 2020/4/13.
 */
public class FlagThreadExit {
    static class MyTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("i will start working");
            while (!closed && !isInterrupted()) {

            }
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }


}
