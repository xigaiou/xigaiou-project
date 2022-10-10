package com.xigaiou.xigaiouproject.domain.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component //注册bean
@Data
public class Cat {
    private String name;
    private Integer age;
}
