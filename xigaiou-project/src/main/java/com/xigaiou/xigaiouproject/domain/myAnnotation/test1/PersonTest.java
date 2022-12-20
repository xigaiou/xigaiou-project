package com.xigaiou.xigaiouproject.domain.myAnnotation.test1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PersonTest {
    String name() default "";
    String sex() default "";
    int age() default 18;
}

