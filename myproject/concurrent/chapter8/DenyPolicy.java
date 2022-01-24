package concurrent.chapter8;

/**
 * Created by 15151 on 2020/4/28.
 * 拒绝策略接口
 */
public interface DenyPolicy {
    void reject(Runnable runnable, ThreadPool threadPool);

    //该拒绝策略直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    //该策略会向任务提交者抛异常
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //TODO 自定义拒绝异常也可以
            throw new RuntimeException("the runnable will be aborted:" + runnable);
        }
    }

    //该策略会将任务在提交者的当前线程中执行
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutDown()) {
                runnable.run();
            }
        }
    }
}
