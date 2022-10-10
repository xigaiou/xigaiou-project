package com.xigaiou.xigaiouproject.domain.myAnnotation.test3;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) //声明应用在属性上，标志着注释可能出现在Java程序中的语法位置
@Retention(RetentionPolicy.RUNTIME) //运行期生效     SOURCE只保留 < CLASS保留到class文件 < RUNTIME保留到运行时
@Documented //被@Documented标注的注解，其修饰的类在生成文档时，会显示该注解
public @interface Registry {
    String name() default "";
}