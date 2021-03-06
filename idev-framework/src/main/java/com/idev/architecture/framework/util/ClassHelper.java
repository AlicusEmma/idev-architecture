package com.idev.architecture.framework.util;

import com.idev.architecture.framework.annonation.Controller;
import com.idev.architecture.framework.annonation.Service;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ClassHelper {

    private static final Set<Class<?>> classSet;

    static{
        String basePackage = "";
        classSet = ClassLoaderUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return classSet;
    }

    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

    public static Set<Class<?>> getContrllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

    
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getContrllerClassSet());
        classSet.addAll(getServiceClassSet());

        return classSet;
    }

    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet){
            if(superClass.isAssignableFrom(cls)
                    && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(
            Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : classSet){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }

        return classSet;
    }
}
