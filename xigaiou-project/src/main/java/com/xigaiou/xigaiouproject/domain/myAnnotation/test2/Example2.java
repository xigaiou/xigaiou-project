package com.xigaiou.xigaiouproject.domain.myAnnotation.test2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * 类扫描(使用spring工具包的spring-core)，然后反射
 */
public class Example2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //动态生成Bean
        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);
        //                                          添加一个包含过滤器
        classPathScanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Cat2.class));
        //                                                                                查找所有符合条件的BeanDefinition
        Set<BeanDefinition> beanDefinitions = classPathScanningCandidateComponentProvider.findCandidateComponents("com.xigaiou.xigaiouproject.domain.myAnnotation.test2");
        for(BeanDefinition beanDefinition : beanDefinitions){
            String beanClassName = beanDefinition.getBeanClassName();
            Class<?> clazz = Class.forName(beanClassName);
            Cat2 cat = (Cat2) clazz.getDeclaredAnnotation(Cat2.class);
            String name = cat.name();
            Object object = clazz.getDeclaredConstructor(String.class).newInstance(name);
            System.out.println(object);
        }
    }
}