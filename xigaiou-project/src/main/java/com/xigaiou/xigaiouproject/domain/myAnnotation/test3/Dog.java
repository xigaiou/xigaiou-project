package com.xigaiou.xigaiouproject.domain.myAnnotation.test3;

@Registry(name = "Wolf")
public class Dog {
    private String name;

    public Dog(){}

    public Dog(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog{" + "name='" + name + '\'' + '}';
    }
}