package com.idev.architecture.framework.proxy.custom;

public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Exception, Throwable;
}
