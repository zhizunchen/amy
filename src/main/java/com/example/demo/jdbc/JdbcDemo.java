package com.example.demo.jdbc;

import java.sql.*;

/**
 * @Created by chenhe
 * @Date 2019-06-14 10:23
 * @Description TODO
 */
public class JdbcDemo {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){}
    }

    private static Connection getConn(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1234567890");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn = JdbcDemo.getConn();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("sql");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
