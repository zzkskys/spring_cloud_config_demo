package cn.zzk.eurekasvr.product;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
public class Organization {
    private String id;

    private String name;

    public Organization(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
