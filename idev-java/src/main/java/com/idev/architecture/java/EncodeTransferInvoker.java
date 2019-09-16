package com.idev.architecture.java;

import java.sql.*;

public class EncodeTransferInvoker {

    public static void main(String[] args){
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // new oracle.jdbc.driver.OracleDriver();
            //通过Class静态类中的forName（）方法加载数据库驱动
            conn = DriverManager.getConnection("jdbc:oracle:thin:@168.168.10.42:1521:ORCL",
                    "huolinhe", "huolinhe");
            // 建立连接，加载驱动类并在DriverManager类中注册后，即可以通过
            //getConnection()方法发出请求连接，
            //参数1表示数据库的URL地址，
            //参数2是用户名，参数3是密码。
            stmt = conn.createStatement();
            // 创建statement对象
            rs = stmt.executeQuery("select 1 from dual");
            // 执行executeQuery()方法，用于产生单个结果集。
            while (rs.next()) {
                System.out.println(rs.getString("empno"));
                System.out.println(rs.getString("ename"));
                System.out.println(rs.getString("deptno"));
                System.out.println(rs.getInt("sal"));
                // System.out.println(rs.getInt("deptno"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
