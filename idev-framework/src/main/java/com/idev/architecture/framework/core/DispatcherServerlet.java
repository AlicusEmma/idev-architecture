package com.idev.architecture.framework.core;

import com.idev.architecture.framework.bean.Data;
import com.idev.architecture.framework.bean.Handler;
import com.idev.architecture.framework.bean.Param;
import com.idev.architecture.framework.util.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServerlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();

        ServletContext servletContext = servletConfig.getServletContext();

        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/jsp/*");

        ServletRegistration resourceServlet = servletContext.getServletRegistration("default");
        resourceServlet.addMapping("/resource/*");

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod,  requestPath);
        if(handler != null){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<String,Object>();
            //直接请求参数
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }

            //请求参数内容体
            String reqBody = getString(request.getInputStream());
            String[] reqBodyParams = StringUtils.split(reqBody,"&");
            for(String param : reqBodyParams){
                String[] array = StringUtils.split(param, "=");
                String name = array[0];
                String value = array[1];
                paramMap.put(name,value);
            }

            Param param = new Param(paramMap);

            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            if(result instanceof Data){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                String json = JSONUtil.toJSON(result);
                writer.write(json);
                writer.flush();
                writer.close();
            }
        }
    }

    private String getString(InputStream is){
        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){

        }

        return sb.toString();
    }
}
