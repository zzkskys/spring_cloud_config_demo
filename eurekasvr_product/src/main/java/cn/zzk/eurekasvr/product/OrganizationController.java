package cn.zzk.eurekasvr.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final List<Organization> organizations = new ArrayList<>();

    public OrganizationController() {
        organizations.add(new Organization("1", "a"));
        organizations.add(new Organization("2", "b"));
        organizations.add(new Organization("3", "c"));
    }

    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable String id) {
        return organizations
                .stream()
                .filter(it -> it.getId().equals(id))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping
    public List<Organization> findAll() {
        return organizations;
    }
}
