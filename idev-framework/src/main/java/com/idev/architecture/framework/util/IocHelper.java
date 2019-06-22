package com.idev.architecture.framework.util;

import com.idev.architecture.framework.annonation.Inject;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {

    static{
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(!CollectionUtils.isEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> entry : beanMap.entrySet()){
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                for(Field beanField : beanFields){
                    if(beanField.isAnnotationPresent(Inject.class)){
                        Class<?> beanFieldClass = beanField.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if(beanFieldInstance != null){
                            ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                        }
                    }
                }

            }
        }
    }
}
