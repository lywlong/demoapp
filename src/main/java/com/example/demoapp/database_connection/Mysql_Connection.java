package com.example.demoapp.database_connection;

import java.sql.*;

public class Mysql_Connection {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动连接成功");
            String url = "jdbc:mysql://localhost:3306/demodata?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
            String username = "root";
            String password = "6230928";
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("已成功的与mysql数据库建立连接！");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 批量插入
     */
    public static int batchInsert(String sql){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void batchExe(){
        String sql = "insert user_tb select '0','2020-03-28 21:37:00','jiao','2020-03-28 21:37:50'"+
                " union all select '0','2020-03-28 21:37:00','jiao1','2020-03-28 21:37:50'"+
                " union all select '0','2020-03-28 21:37:00','jiao2','2020-03-28 21:37:50'";
        int res = batchInsert(sql);
        if(res>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

    public ResultSet queryTableName(){
        String sql = "show tables";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet!=null){
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        batchExe();
    }
}
