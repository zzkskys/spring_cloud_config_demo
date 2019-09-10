package cn.zzk.eurekasvr.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@RestController
@RequestMapping("/hello")
public class HelloWordController {

    @GetMapping
    public String hello() {
        return "Hello , I am organization service.The present time is " + System.currentTimeMillis();
    }
}
