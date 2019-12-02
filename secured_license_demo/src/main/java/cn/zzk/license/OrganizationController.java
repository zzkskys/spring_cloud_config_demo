package cn.zzk.license;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Create Time : 2019/09/25
 *
 * @author zzk
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationClient client;

    public OrganizationController(OrganizationClient client) {
        this.client = client;
    }

    @GetMapping("/{id}")
    public Organization organization(@PathVariable String id) {
        return client.getOrganization();
    }
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        return client.hello();
    }

    @GetMapping("/principle")
    public String getPrinciple() {
        return "principle";
    }
}
