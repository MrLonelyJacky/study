package concurrent.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/3/25.
 * one demo to show what is concurrent code
 * if u want to watch news meanwhile listen to music
 * two threads  main thread browseNews
 * thread-0 enjoyMusic
 */
public class ConcurrentDemo {
    public static void main(String[] args) {
        new Thread(() -> enjoyMusic()).start();
        browseNews();
    }

    private static void browseNews() {
        for (;;){
            System.out.println("uhh good news");
            sleep(1);
        }
    }

    /**
     * enjoy music and sleep thread
     */
    private static void enjoyMusic() {
        for (;;){
            System.out.println("uhh nice music");
            //sleep the thread for another thread execute
            sleep(1);
        }
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
