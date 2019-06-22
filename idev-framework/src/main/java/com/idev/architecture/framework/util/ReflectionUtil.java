package com.idev.architecture.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance;
        try{
            instance = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }

        return instance;
    }


    public static Object invokeMethod(Object obj, Method method,
                                      Object... args){
        Object result = null;
        try{
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch (Exception e){

        }

        return result;
    }

    public static void setField(Object obj, Field field, Object... args){
        try{
            field.setAccessible(true);
            field.set(obj,args);
        }catch (Exception e){

        }
    }



}
