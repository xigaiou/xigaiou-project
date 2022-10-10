package com.xigaiou.xigaiouproject.domain.myAnnotation.test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//声明一个类为配置类，用于取代bean.xml
@ComponentScan("com.xigaiou.xigaiouproject.domain.myAnnotation.test3")
public class Example3 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Example3.class);
        Dog dog = context.getBean(Dog.class);
        System.out.println(dog);
    }
}