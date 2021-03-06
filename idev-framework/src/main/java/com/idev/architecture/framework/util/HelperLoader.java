package com.idev.architecture.framework.util;

import org.springframework.util.ClassUtils;

public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls : classList){
            ClassLoaderUtil.loadClass(cls.getName(), false);
        }

    }


}
