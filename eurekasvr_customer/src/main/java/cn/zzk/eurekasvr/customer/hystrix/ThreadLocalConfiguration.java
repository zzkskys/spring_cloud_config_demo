package cn.zzk.eurekasvr.customer.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Create Time : 2019/09/12
 *
 * @author zzk
 */
@Configuration
public class ThreadLocalConfiguration {

    @Autowired(required = false)
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;

    /**
     * 因为要注册一个新的并发策略，所以要获取其他的 Hystrix组件，然后重新设置 Hystrix 插件
     */
    @PostConstruct
    public void init() {
        // Keeps references of existing Hystrix plugins.
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance()
                .getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance()
                .getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
                .getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance()
                .getCommandExecutionHook();

        HystrixPlugins.reset();

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new ThreadLocalAwareStrategy(existingConcurrencyStrategy));
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
    }
}
