package com.xigaiou.xigaiouproject.domain.myAnnotation.test1;

import lombok.Data;

@Data
public class Human {
    private String name;
    private String sex;
    private Integer age;

    @Override
    public String toString() {
        return "Human2{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}