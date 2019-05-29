package com.shiyanlou.demo;

import com.shiyanlou.demo.config.Client;
import com.shiyanlou.demo.config.SpringConfig;
import com.shiyanlou.demo.helloworld.HelloWorld;
import com.shiyanlou.demo.entity.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
    //private static ApplicationContext context;

    public static void main(String[] args) {
        //context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        //Dog dog = (Dog) context.getBean("dogBean");
        //System.out.println(dog);

        //AbstractApplicationContext abstractApplicationContext=new ClassPathXmlApplicationContext("SpringBeans.xml");

        //HelloWorld helloWorld = (HelloWorld)abstractApplicationContext.getBean("helloWorld");
        //helloWorld.getMessage();
        //abstractApplicationContext.registerShutdownHook();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Client client = context.getBean("client", Client.class);
        client.invokeService();
    }
}