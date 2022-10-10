package com.xigaiou.xigaiouproject.domain.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;


/*
@PropertySource ：加载指定的配置文件；
@ConfigurationProperties : 默认从全局配置文件中获取值；
@ConfigurationProperties的作用是将配置文件中配置的每一个属性的值，映射到这个组件中；
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix = “person” : 将配置文件中的person下面的所有属性一一对应
*/
@Component //注册bean到容器中
@Data
@ConfigurationProperties(prefix = "person")
@Validated
//@PropertySource(value = "classpath:person.properties")//加载指定的配置文件
public class Person {
    private String id;
    //@Value("${name}")
    private String name;
    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String email;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Cat cat;
}
