package com.idev.architecture.framework.aop.study;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args,
                              Object target, Exception e){

        System.out.println("Throw Exception.....");
    }

}
