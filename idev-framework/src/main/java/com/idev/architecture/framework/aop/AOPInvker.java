package com.idev.architecture.framework.aop;

import com.idev.architecture.framework.proxy.Hello;
import com.idev.architecture.framework.proxy.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

public class AOPInvker {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        Hello hello  = (Hello) proxyFactory.getProxy();
        hello.sayHello("Kobe");

    }

}
