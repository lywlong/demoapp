package com.example.demoapp.database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServer_Connection {

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServer");
            System.out.println("数据库驱动加载成功");
            //SQL server 2005 连接信息
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_database22";
            String user = "sa";
            String password = "password";
            connection = DriverManager.getConnection(url,user,password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
