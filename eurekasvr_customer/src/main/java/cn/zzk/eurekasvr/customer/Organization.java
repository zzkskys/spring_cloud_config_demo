package cn.zzk.eurekasvr.customer;

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

    public Organization() {
        // jackson 需要调用的无参构造器
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
