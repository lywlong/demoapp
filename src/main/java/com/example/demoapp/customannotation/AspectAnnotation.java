package com.example.demoapp.customannotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectAnnotation {

    String desc() default "无信息";

}
