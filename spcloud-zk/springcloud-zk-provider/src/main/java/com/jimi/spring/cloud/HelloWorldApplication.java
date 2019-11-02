package com.jimi.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 叶宪耀(xianyao.ye @ ucarinc.com)
 * @Date Create date 2019/11/2 16:35
 * <p>类的说明</p>
 * @since 1.0.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}