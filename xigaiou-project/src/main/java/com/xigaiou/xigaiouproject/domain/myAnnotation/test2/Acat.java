package com.xigaiou.xigaiouproject.domain.myAnnotation.test2;

@Cat2(name="tom")
public class Acat {
    private String name;

    public Acat(){}

    public Acat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Acat{" + "name='" + name + '\'' + '}';
    }
}