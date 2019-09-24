package cn.zzk.eurekasvr.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    private Logger log = LoggerFactory.getLogger(OrganizationController.class);

    public OrganizationController() {
        organizations.add(new Organization("1", "a"));
        organizations.add(new Organization("2", "b"));
        organizations.add(new Organization("3", "c"));
    }

    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable String id, HttpServletRequest request) throws InterruptedException {
        String token = request.getHeader("x-token");

        if (token == null) {
            log.info("x-token is null");
        } else {
            log.info("x-token is {}", token);
        }

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
