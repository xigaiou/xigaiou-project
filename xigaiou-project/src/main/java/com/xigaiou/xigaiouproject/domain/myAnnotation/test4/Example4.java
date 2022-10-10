package com.xigaiou.xigaiouproject.domain.myAnnotation.test4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.xigaiou.xigaiouproject.domain.myAnnotation.test4")
public class Example4 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Example4.class);
        Hello hello = context.getBean(Hello.class);
        hello.say();
    }
}