package com.dorr.spring.neo4j.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Before(value = "execution (* com.dorr.spring.neo4j.poem.controller.*.*(..))")
    public void beforeModel(JoinPoint joinPoint) throws IOException{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        LogType logType = method.getAnnotation(LogType.class);

        if (logType !=null && logType.value() != null ){
            System.out.println("开始" + logType.value() + "    --------" );
        }
    }

    @After(value = "execution (* com.dorr.spring.neo4j.poem.controller.*.*(..))")
    public void afterModel(JoinPoint joinPoint) throws IOException{

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        LogType logType = method.getAnnotation(LogType.class);

        if (logType !=null && logType.value() != null ){
            System.out.println("完成" + logType.value() + "    --------" );
        }
    }
}
