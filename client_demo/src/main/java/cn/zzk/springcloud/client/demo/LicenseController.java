package cn.zzk.springcloud.client.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create Time : 2019/07/21
 *
 * @author zzk
 */
@RestController
@RefreshScope
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    private LicenseRepo licenseRepo;

    @Value("${example.property}")
    private String description;

    @PostMapping
    public License saveLicense(@RequestBody License license) {
        return licenseRepo.save(license);
    }

    @GetMapping
    public List<License> findAll() {
        return licenseRepo.findAll();
    }

    @GetMapping("/show")
    public String getDescription() {
        return description;
    }
}
