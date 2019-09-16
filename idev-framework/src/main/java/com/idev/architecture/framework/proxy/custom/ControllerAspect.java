package com.idev.architecture.framework.proxy.custom;

import com.idev.architecture.framework.aop.custom.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug("-------------------------begin-----------------------------");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.debug("-------------------------after-----------------------------");
    }
}
