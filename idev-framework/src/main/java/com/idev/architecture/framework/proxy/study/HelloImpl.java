package com.idev.architecture.framework.proxy.study;

public class HelloImpl implements Hello {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello!" + name);
        throw new RuntimeException("HelloImple has an error");
    }
}
