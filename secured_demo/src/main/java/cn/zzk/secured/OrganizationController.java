package cn.zzk.secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Create Time : 2019/09/25
 *
 * @author zzk
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private List<Organization> list = new ArrayList<>();

    public OrganizationController() {
        list.add(new Organization("1", "a"));
        list.add(new Organization("2", "b"));
        list.add(new Organization("3", "c"));
    }

    @GetMapping
    public List<Organization> list() {
        return list;
    }

    @GetMapping("/{id}")
    public Organization organization(@PathVariable String id) {
        return list.stream().filter(it -> it.getId().equals(id)).findAny().orElse(new Organization("unknown", "unknown"));
    }
}

class Organization {
    private String id = UUID.randomUUID().toString();

    private String name;

    public Organization(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
