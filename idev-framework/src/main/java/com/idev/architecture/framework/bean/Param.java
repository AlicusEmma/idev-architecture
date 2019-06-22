package com.idev.architecture.framework.bean;

import java.util.Map;

public class Param {

    private Map<String,Object> paramsMap;

    public Param(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public long getLong(String name){
        return Long.parseLong(String.valueOf(paramsMap.get(name)));
    }

    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }
}
