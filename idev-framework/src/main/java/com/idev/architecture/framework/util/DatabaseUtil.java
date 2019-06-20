package com.idev.architecture.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    private static final Logger log = LoggerFactory.getLogger(DatabaseUtil.class);

    private static String driver;

    private static String url;

    private static String userName;

    private static String password;

    static{
        Properties prop = PropUtil.loadProps("config.properties");
        driver = prop.getProperty("jdbc.driver");
        url = prop.getProperty("jdbc.url");
        userName = prop.getProperty("jdbc.username");
        password = prop.getProperty("jdbc.password");

        try {
            Class.forName(driver);
        }catch (Exception e){
            log.error("can not load jdbc driver ");
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,userName,password);
        }catch (SQLException e){
            log.error("get connection error ", e);
        }

        return conn;
    }

    public static void close(Connection conn){
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                log.error("conneciton close error", e);
            }
        }
    }




}
