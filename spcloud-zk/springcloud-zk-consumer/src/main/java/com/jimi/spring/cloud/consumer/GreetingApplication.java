package com.jimi.spring.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 叶宪耀(xianyao.ye @ ucarinc.com)
 * @Date Create date 2019/11/2 16:52
 * <p>类的说明</p>
 * @since 1.0.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GreetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingApplication.class, args);
    }
}