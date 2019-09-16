package com.idev.architecture.framework.proxy.custom;

import com.idev.architecture.framework.annonation.Transaction;
import com.idev.architecture.framework.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 事务代理
 */
public class TransaxtionProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(TransaxtionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Exception, Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if(!flag && method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try{
                DatabaseUtil.beginTransaction();
                result = proxyChain.doProxyChain();
                DatabaseUtil.commitTransaction();
            }catch (Exception e){
                DatabaseUtil.rollbackTransaction();
                throw e;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            result = proxyChain.doProxyChain();
        }

        return result;
    }
}
