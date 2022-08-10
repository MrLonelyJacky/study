package netty.chapter2.pseudovAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeHandlerExecutePool {
    private ExecutorService executorService;

    public TimeHandlerExecutePool(int maxPool,int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPool,120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
