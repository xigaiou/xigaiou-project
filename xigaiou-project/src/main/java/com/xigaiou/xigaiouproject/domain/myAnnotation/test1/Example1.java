package com.xigaiou.xigaiouproject.domain.myAnnotation.test1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 1.不基于spring容器实现
 * (1). 已知class，直接反射
 */
public class Example1 {

    @Person(name="张三", sex = "男", age = 23)
    private Human human1;

    @Person(name="小红", sex = "女", age = 21)
    private Human human2;
    //                                              没有这样的方法            没有访问权限        多加一层间接层来提供统一的访问途径    无法实例化指定的类对象
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Example1 example01 = new Example1();
        System.out.println(example01.human1);
        System.out.println(example01.human2);
        example01.initField();
        System.out.println(example01.human1);
        System.out.println(example01.human2);
    }
    // 注解实现
    public void initField() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends Example1> clazz = this.getClass();//返回此对象的运行时类
        Field[] fields = clazz.getDeclaredFields();//返回一个Field对象数组，该数组反映由这个class对象表示的类或接口声明的所有字段
        for(Field field : fields){
            Person person = field.getDeclaredAnnotation(Person.class);//仅仅返回"直接修饰"注解
            if(person != null) {
                Human human = ((Class<Human>) field.getType()).getDeclaredConstructor().newInstance();
                human.setSex(person.sex());
                human.setName(person.name());
                human.setAge(person.age());
                field.set(this, human);
            }
        }
    }
}