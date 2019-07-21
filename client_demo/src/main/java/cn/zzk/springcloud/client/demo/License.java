package cn.zzk.springcloud.client.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Create Time : 2019/07/21
 *
 * @author zzk
 */
@Entity
@Getter
@Setter
public class License {
    @Id
    @Column(length = 36)
    private String lecenseId = UUID.randomUUID().toString();

    private String organizationId;

    private String productName;
}
