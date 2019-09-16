package com.idev.architecture.framework.aop.study;

import com.idev.architecture.framework.proxy.study.Hello;
import com.idev.architecture.framework.proxy.study.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

public class AOPInvoker {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        Hello hello  = (Hello) proxyFactory.getProxy();
        hello.sayHello("Kobe");

    }

}
