package com.jimi.spring.cloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  
    @Autowired
    private HelloWorldClient helloWorldClient;
 
    @GetMapping("/get-greeting")
    public String greeting() {
        return helloWorldClient.helloWorld();
    }
}