package com.pl.demo.handler.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * author pengliang  2018-05-07 22:37
 */
@Aspect
@Component
public class MyAspect {
    /**
     * 环绕通知
     *
     * @param pjp 当前切入方法上下文信息
     * @return
     */
    @Around("execution(* com.pl.demo.controller.*.*(..))")
    public Object handlerControllerTest(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("开始切入");
        //获取到切入方法参数信息
        Object[] objects = pjp.getArgs();
        Arrays.stream(objects).forEach(d -> {
            System.out.println(d);
        });
        //相当doFilter
        Object object = pjp.proceed();
        System.out.println("结束");
        return object;
    }
    @Pointcut("execution(* com.pl.demo.controller.*.*(..))")
    public void pointcutTest(){

    }

    @Before("pointcutTest()")
    public void beforeTest() {
        System.out.println("beforeTest");
    }
}
