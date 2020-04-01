package com.example.demoapp.service.mongodb_service;

import com.example.demoapp.mongdb_package.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * mongoTemplate.updateFirst操作：修改第一条
 * mongoTemplate.updateMulti操作：修改符合条件的所有
 * this.mongoTemplate.upsert操作：修改时如果不存在则添加.
 *
 */
@Slf4j
@Service
public class MongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;
    public String saveBook(Book book){
        log.info("------:mongodb save start");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        mongoTemplate.save(book);
        return "save success";
    }

    public List<Book> findAll(){
        log.info("mongodb find start...");
        return mongoTemplate.findAll(Book.class);
    }

    public Book findBookById(String id){
        log.info("find one by id");
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query,Book.class);
    }

    public String updateBook(Book book){
        log.info("mongodb update start");
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update()
                .set("publish",book.getPublish())
                .set("info",book.getInfo())
                .set("updateTime",book.getUpdateTime());
        mongoTemplate.updateFirst(query,update,Book.class);
        //mongoTemplate.updateMulti(query,update,Book.class);
        //mongoTemplate.upsert(query,update,Book.class);
        return "ok";
    }

    public String deleteBook(Book book){
        log.info("mongodb delete object start");
        mongoTemplate.remove(book);
        return "ok";
    }

    public String deleteBook(String id){
        log.info("delete object by id");
        Book book = findBookById(id);
        if (book!=null){
            deleteBook(book);
            return "ok";
        }
        return "fail";
    }

}
