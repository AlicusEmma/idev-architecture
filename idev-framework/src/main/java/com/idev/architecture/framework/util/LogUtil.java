package com.idev.architecture.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);


    public static void main(String[] args) {
        logger.trace("====== trace");
        logger.debug("====== debug");
        logger.info("====== info");
        logger.warn("====== warn");
        logger.error("====== error");

    }

}
