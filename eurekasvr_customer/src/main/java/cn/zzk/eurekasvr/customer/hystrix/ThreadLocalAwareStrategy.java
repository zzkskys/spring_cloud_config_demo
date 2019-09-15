package cn.zzk.eurekasvr.customer.hystrix;

import cn.zzk.eurekasvr.customer.utils.UserContextHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create Time : 2019/09/12
 *
 * @author zzk
 */
public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    /**
     * 将 Spring Cloud 已经定义的并发类传入
     */
    public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy existingConcurrency) {
        this.existingConcurrencyStrategy = existingConcurrency;
    }


    /**
     * 重写方法，要么使用 Spring Cloud 提供的并发策略，要么使用 父类的并发策略
     */
    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getBlockingQueue(maxQueueSize)
                : super.getBlockingQueue(maxQueueSize);
    }

    /**
     * 重写方法，要么使用 Spring Cloud 提供的并发策略，要么使用 父类的并发策略
     */
    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(
            HystrixRequestVariableLifecycle<T> rv) {
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getRequestVariable(rv)
                : super.getRequestVariable(rv);
    }

    /**
     * 重写方法，要么使用 Spring Cloud 提供的并发策略，要么使用 父类的并发策略
     */
    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
                                            HystrixProperty<Integer> corePoolSize,
                                            HystrixProperty<Integer> maximumPoolSize,
                                            HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy.getThreadPool(threadPoolKey, corePoolSize,
                maximumPoolSize, keepAliveTime, unit, workQueue)
                : super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize,
                keepAliveTime, unit, workQueue);
    }

    /**
     * 将 UserContextHolder 注入
     */
    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return existingConcurrencyStrategy != null
                ? existingConcurrencyStrategy
                .wrapCallable(new DelegatingUserContextCallable<T>(callable, UserContextHolder.getContext()))
                : super.wrapCallable(new DelegatingUserContextCallable<T>(callable, UserContextHolder.getContext()));
    }
}
