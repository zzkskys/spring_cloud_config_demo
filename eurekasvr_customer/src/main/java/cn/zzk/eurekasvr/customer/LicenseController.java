package cn.zzk.eurekasvr.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Autowired
    private OrganizationClient client;

    @Autowired
    private OrganizationFeignClient feignClient;

    @PutMapping("/by-rest")
    public License created(String phone, String organizationId) {
        Organization organization = client.getByRest(organizationId);
        return new License(UUID.randomUUID().toString(), phone, organization.getId(), organization.getName());
    }

    @PutMapping("/by-discovery-client")
    public License byDiscovery(String phone, String organizationId) {
        Organization organization = client.getByDiscoveryClient(organizationId);
        return new License(UUID.randomUUID().toString(), phone, organization.getId(), organization.getName());
    }

    @PutMapping("/by-ribbon")
    public License byRibbon(String phone, String organizationId) {
        Organization organization = client.getBySupportRibbon(organizationId);
        return new License(UUID.randomUUID().toString(), phone, organization.getId(), organization.getName());
    }

    @PutMapping("/by-feign")
    public License byFeign(String phone, String organizationId) {
        Organization organization = feignClient.getOrganization(organizationId);
        return new License(UUID.randomUUID().toString(), phone, organization.getId(), organization.getName());
    }
}
