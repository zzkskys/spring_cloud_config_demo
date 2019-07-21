package cn.zzk.springcloud.client.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create Time : 2019/07/21
 *
 * @author zzk
 */
public interface LicenseRepo extends JpaRepository<License, String> {
}
