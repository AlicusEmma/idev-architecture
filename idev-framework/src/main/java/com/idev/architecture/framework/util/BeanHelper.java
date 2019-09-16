package com.idev.architecture.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>,Object>();


    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass : beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            beanMap.put(beanClass, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 泛型案例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if(!beanMap.containsKey(cls)){

        }

        return (T) beanMap.get(cls);
    }

    public static void setBean(Class<?> cls, Object obj){
        beanMap.put(cls, obj);
    }
}
