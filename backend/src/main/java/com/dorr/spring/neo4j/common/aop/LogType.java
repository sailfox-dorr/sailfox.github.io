package com.dorr.spring.neo4j.common.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogType {
    String value() default "";
}
