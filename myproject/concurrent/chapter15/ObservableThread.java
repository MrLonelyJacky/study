package concurrent.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/5/13.
 * 事件发布者  设计成final 或者私有构造器提供静态工厂 防止子类重写run方法
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    private ObservableThread(Task<T> task) {
        this(task, new EmptyLifeCycle<>());
    }

    private ObservableThread(Task<T> task, TaskLifeCycle<T> taskLifeCycle) {
        if (task == null) {
            throw new IllegalArgumentException("the task is required");
        }
        this.task = task;
        this.lifeCycle = taskLifeCycle;
    }

    @Override
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        this.update(Cycle.RUNNING, null, null);
        T call = this.task.call();
        this.update(Cycle.DONE, call, null);

    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        switch (cycle) {
            case STARTED:
                this.lifeCycle.onStart(currentThread());
                break;
            case RUNNING:
                this.lifeCycle.onRunning(currentThread());
                break;
            case DONE:
                this.lifeCycle.onFinish(currentThread(), result);
                break;
            case ERROR:
                this.lifeCycle.onError(currentThread(), e);
                break;
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    /**
     * 静态工厂
     *
     * @param task
     * @param <T>
     * @return
     */
    public static <T> Observable getInstance(Task<T> task) {
        return new ObservableThread<>(task);
    }

    public static <T> Observable getInstance(Task<T> task, TaskLifeCycle<T> taskLifeCycle) {
        return new ObservableThread<>(task, taskLifeCycle);
    }

    public static void main(String[] args) {
        Observable observableThread = new ObservableThread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done");
            return null;
        });
        observableThread.start();
        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle<String>() {
            @Override
            public void onStart(Thread thread) {

            }

            @Override
            public void onRunning(Thread thread) {

            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println(thread.getName() + "the result is" + result);
            }

            @Override
            public void onError(Thread thread, Exception e) {

            }
        };

    }


}
