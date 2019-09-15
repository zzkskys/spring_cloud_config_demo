package cn.zzk.eurekasvr.customer.hystrix;

import cn.zzk.eurekasvr.customer.utils.UserContext;
import cn.zzk.eurekasvr.customer.utils.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * Create Time : 2019/09/12
 *
 * @author zzk
 */
public class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext originalUserContext;

    /**
     * 原始 Callable 类将被传递到 自定义的 Callable 类，自定义 Callable 将调用 Hystrix 保护的代码和来自父线程的 UserContext
     *
     */
    public DelegatingUserContextCallable(Callable<V> delegate,
                                         UserContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    /**
     * UserContextHolder 设置之后，在 Hystrix 保护的方法上调用 call() 方法
     */
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }


    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
