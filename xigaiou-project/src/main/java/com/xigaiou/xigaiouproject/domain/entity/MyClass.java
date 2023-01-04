package com.xigaiou.xigaiouproject.domain.entity;

public class MyClass {
    private String value;
    public MyClass(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "value:" + value;
    }
}
