package com.idev.architecture.framework.util;

import com.idev.architecture.framework.annonation.Action;
import com.idev.architecture.framework.bean.Handler;
import com.idev.architecture.framework.bean.Request;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Map<Request, Handler> actionMap = new HashMap<Request, Handler>();

    static {

        Set<Class<?>> controllerClassSet = ClassHelper.getContrllerClassSet();
        if(!CollectionUtils.isEmpty(controllerClassSet)){
           for(Class<?> conrollerClass : controllerClassSet){
               Method[] methods = conrollerClass.getDeclaredMethods();
               for(Method method : methods){
                   if(method.isAnnotationPresent(Action.class)){
                       Action action = method.getAnnotation(Action.class);
                       String mapping = action.value();
                       if(mapping.matches("\\w+:/\\w*")){
                           String[] array = mapping.split(":");
                           Request request = new Request(array[0], array[1]);
                           Handler hander = new Handler(conrollerClass,method);
                           actionMap.put(request,hander);
                       }
                   }
               }
           }

        }
    }


    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return actionMap.get(request);
    }
}
