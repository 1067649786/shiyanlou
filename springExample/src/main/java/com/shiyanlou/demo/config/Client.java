package com.shiyanlou.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

public class Client {
    @Autowired
    Service service;
    public void invokeService(){
        System.out.println("client invoke :" + service.sayHello());
    }

}
