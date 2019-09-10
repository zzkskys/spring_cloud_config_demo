package cn.zzk.eurekasvr.customer;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
public class License {

    private String id;

    private String phone;

    private String organizationId;

    private String organizationName;

    public License(String id, String phone, String organizationId, String organizationName) {
        this.id = id;
        this.phone = phone;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
