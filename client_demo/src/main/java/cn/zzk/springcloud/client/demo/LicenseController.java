package cn.zzk.springcloud.client.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create Time : 2019/07/21
 *
 * @author zzk
 */
@RestController
@RequestMapping("/license")
@AllArgsConstructor
public class LicenseController {

    private final LicenseRepo licenseRepo;


    @PostMapping
    public License saveLicense(@RequestBody License license) {
        return licenseRepo.save(license);
    }

    @GetMapping
    public List<License> findAll() {
        return licenseRepo.findAll();
    }
}
