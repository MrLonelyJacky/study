package spring.chapter7.service;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultUsageTracked implements UsageTracked{

    private static AtomicInteger count = new AtomicInteger();

    @Override
    public Integer incrementUseCount() {
        return count.incrementAndGet();
    }
}
