package cn.zzk.eurekasvr.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@Component
public class OrganizationClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private RestTemplate template;

    /**
     * 传统的通过 HTTP 硬编码来消费服务
     */
    public Organization getByRest(String id) {
        return restTemplate.getForObject("http://localhost:8760/organizations/{id}", Organization.class, id);
    }

    /**
     * 通过 DiscoveryClient 消费服务。
     * 在实际运用中，只有在服务需要查询 Ribbon 以了解哪些服务和服务实例已经通过它注册时，才应该使用DiscoveryClient。
     * 代码存在以下问题 :
     * 1. 没有利用 Ribbon 的客户端负载均衡 -- 尽管通过直接调用 DiscoveryClient 可以获得服务列表，但是要调用哪些返回的服务实例就成为了开发人员的责任
     * 2. 开发人员做了太多的工作 -- 开发人员必须构建一个用来调用服务的 URL.警棍这是小事，但是编写的代码越少意味着需要调试的代码越少
     */
    public Organization getByDiscoveryClient(String organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");
        if (instances.isEmpty()) {
            return null;
        }
        String url = instances.get(0).getUri().toString();
        return restTemplate.getForObject(url + "/organizations/{organizationId}", Organization.class, organizationId);
    }

    public Organization getBySupportRibbon(String organizationId) {
        return template.getForObject("http://organizationservice/organizations/{organizationId}", Organization.class, organizationId);
    }

}

/**
 * 在使用标准的 Spring RestTemplate 类时，所有服务调用 的 HTTP 状态码都通过 ResponseEntity 类的 getStatusCode() 方法返回。
 * 通过 Feign 客户端，任何被调用的服务返回的 HTTP 状态码 在 4xx-5xx 返回内的都将被映射为 FeignException。 FeignException
 * 包含可以被解析为特定错误消息的 JSON 实体。
 * Feign 为开发人员提供了编写错误解码器类的功能，该类可以将错误映射为自定义的异常类。有关错误解码器的内容可通过 Feign GitHub 存储库中查找。-*-
 */
@FeignClient("organizationservice")
interface OrganizationFeignClient {
    @GetMapping("/organizations/{organizationId}")
    Organization getOrganization(@PathVariable String organizationId);
}
