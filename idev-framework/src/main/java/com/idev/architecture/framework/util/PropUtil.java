package com.idev.architecture.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropUtil.class);

    public static Properties loadProps(String fileName){
        Properties props  = null;
        InputStream is = null;

        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is == null){
                throw new FileNotFoundException(fileName + " is not found");
            }
            props = new Properties();
            props.load(is);
        }catch (IOException e){
            LOGGER.error("Load Properties file failure",e);
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return props;
    }

    public String getProperty(Properties property, String key){
        return getProperty(property, key,"");
    }

    public String getProperty(Properties property, String key,
                              String defaultValue){
        String value = defaultValue;
        if(property.contains(key)){
            value = property.getProperty(key);
        }

        return value;
    }

}
