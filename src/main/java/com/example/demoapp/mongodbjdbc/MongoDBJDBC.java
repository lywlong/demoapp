package com.example.demoapp.mongodbjdbc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {
    public static void main(String[] args) {
        //连接到mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully");
    }
}
