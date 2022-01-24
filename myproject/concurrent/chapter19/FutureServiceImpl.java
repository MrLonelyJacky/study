package concurrent.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    private static final String FUTURE_THREAD_PREFIX = "FUTURE_";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<Void> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            //任务完执行完成后将null传递给future
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
            //执行回调接口
            if (callback != null) {
                callback.call(result);
            }
        },getNextName()).start();
        return futureTask;
    }
}
