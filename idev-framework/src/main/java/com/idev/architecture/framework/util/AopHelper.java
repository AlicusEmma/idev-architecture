package com.idev.architecture.framework.util;

import com.idev.architecture.framework.annonation.Transaction;
import com.idev.architecture.framework.aop.custom.Aspect;
import com.idev.architecture.framework.proxy.custom.Proxy;
import com.idev.architecture.framework.proxy.custom.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.*;

public class AopHelper {

    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static{
        try{
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);

            for (Map.Entry<Class<?>,List<Proxy>> targetEntry: targetMap.entrySet()) {
                Class<?> proxyClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(proxyClass, proxyList);
                BeanHelper.setBean(proxyClass,proxy);
            }
        }catch (Exception e){
            logger.error("error aop", e);
        }
    }

    public static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    public static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 添加统一的事务处理机制
     * @param proxyMap
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>>  serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(Transaction.class, serviceClassSet);

    }

    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(Aspect.class);
        for(Class<?> proxyClass : proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }

    public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>, List<Proxy>>  targetProxyMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>,   Set<Class<?>>> proxyEntry: proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass: targetClassSet) {
                Proxy proxy = (Proxy) targetClass.newInstance();
                if(targetProxyMap.containsKey(targetClass)){
                    targetProxyMap.get(targetClass).add(proxy);
                }else{
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetProxyMap.put(targetClass, proxyList);
                }
            }
        }

        return targetProxyMap;
    }



}
