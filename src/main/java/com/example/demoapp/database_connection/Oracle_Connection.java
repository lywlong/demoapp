package com.example.demoapp.database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle_Connection {

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //@：分隔符
            //1521：数据库端口
            //orcl:数据库名或SID
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "system";
            String password = "password";
            connection = DriverManager.getConnection(url,user,password);
            if(connection!=null){
                System.out.println("已成功与Oracle数据库建立连接");
                return connection;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
