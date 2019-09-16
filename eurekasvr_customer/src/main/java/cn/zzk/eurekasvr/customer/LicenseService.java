package cn.zzk.eurekasvr.customer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Create Time : 2019/09/11
 *
 * @author zzk
 */
@Service
public class LicenseService {

    private final List<License> licenses = new ArrayList<>();

    public LicenseService() {
        licenses.add(new License("1", "111", "1", "组织a"));
        licenses.add(new License("2", "222", "2", "组织b"));
        licenses.add(new License("3", "333", "3", "组织c"));
    }

    /**
     * Hystrix 会包装该方法的调用。
     * <p>
     * threadPoolKey 表示要建立新的线程池。
     * 开发人员可以在线程池前创建一个队列，该队列将控制在线程池中线程繁忙时允许堵塞的请求数。若 maxQueueSize 的值为 -1,则将使用
     * Java SynchronousQueue 来保存所有传入的请求。同步队列本质会强制要求正在处理中的请求数量用于不能超过线程池中可用线程的数量。
     * 将 maxQueueSize 的值设置大于 1,则 Hystrix 使用 Java LinkedBlockingQueue 。LinkedBlockingQueue 的使用允许开发人员即使
     * 素有线程都在忙于处理请求，也能对请求进行排队。
     * maxQueueSize 属性只能在线程池首次初始化时设置，也允许通过 queueSizeRejectionThreshold 属性来动态更改队列的大小。
     * Netflix 推荐的自定义线程池的适当大小 ：
     * 服务在健康状态时每秒支撑的最大请求数 * 第99百分位延时时间(以秒为单位) + 用于缓冲的少量额外线程
     */
    @HystrixCommand(
            commandProperties = {
                    //超时时间，默认1000ms
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    //在Hystrix 跳闸之前，10s 内连续调用的数量
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                    //在断路器跳闸之前必须达到的调用失败(由于超时、抛出异常或返回 500)的百分比
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    //断路器跳闸后，允许另一个调用通过以便查看服务是否恢复健康之前Hystrix 的休眠时间
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    //用于控制 Hystrix 来见识服务调用问题的窗口大小，默认 10000ms
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    //控制在定义滚动窗口中收集统计信息的次数。在该示例中， Hystrix 将使用 15s 的窗口，并将统计收集到的长度为 3s 的5个桶中
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")},
            fallbackMethod = "whenBuildFail",
            threadPoolKey = "findByOrgThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")})
    public List<License> findByOrganizationId(String organizationId) {
        mockTimeOut();
        return licenses
                .stream()
                .filter(it -> it.getOrganizationId().equals(organizationId))
                .collect(Collectors.toList());
    }

    /**
     * 模拟网络超时
     */
    private void mockTimeOut() {
        Random random = new Random();
        int randomNum = random.nextInt(3) + 1;
        if (randomNum == 3) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * findByOrganizationId 失败的后备方案.
     * 1. 后备方案是在主要行动方案失败时的候选。因此例如 日志记录等应在主要行动方案中，不必采取后备方案
     * 2. 注意使用后备方法所执行的操作。若后备方法是调用另一个分布式服务，则后备方法也需要使用 @HystrixCommand 断路器
     */
    private List<License> whenBuildFail(String organizationId) {
        return licenses;
    }


}
