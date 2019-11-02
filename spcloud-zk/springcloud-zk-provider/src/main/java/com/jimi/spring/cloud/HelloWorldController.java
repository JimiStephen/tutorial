package com.jimi.spring.cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 叶宪耀(xianyao.ye @ ucarinc.com)
 * @Date Create date 2019/11/2 16:37
 * <p>类的说明</p>
 * @since 1.0.
 */
@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }
}
