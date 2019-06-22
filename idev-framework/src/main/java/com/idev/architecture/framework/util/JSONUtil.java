package com.idev.architecture.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

    public static <T> String toJSON(T obj){
        return JSON.toJSONString(obj);
    }
}
