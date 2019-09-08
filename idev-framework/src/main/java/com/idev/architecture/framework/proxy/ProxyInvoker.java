package com.idev.architecture.framework.proxy;

public class ProxyInvoker {

    public static void main(String[] args) {

//        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
//
//        Hello helloProxy= dynamicProxy.getProxy();
//
//        helloProxy.sayHello("Alicus");

        Hello helloCglibProxy = CGLibDynamicProxy.getInstance()
                .getProxy(HelloImpl.class);
        helloCglibProxy.sayHello("Kobe");

    }
}
