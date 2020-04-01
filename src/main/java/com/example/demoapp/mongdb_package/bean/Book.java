package com.example.demoapp.mongdb_package.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * 图书实体类
 */
@Getter
@Setter
public class Book {

    @Id
    private String id;
    private double price;
    private String name;
    private String info;
    private String publish;
    private Date createTime;
    private Date updateTime;

}
