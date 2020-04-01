package com.example.demoapp.controller.mongodbcontroller;

import com.example.demoapp.mongdb_package.bean.Book;
import com.example.demoapp.service.mongodb_service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class MongodbController {

    @Autowired
    private MongodbService mongodbService;

    @PostMapping("/save")
    public String saveObject(@RequestBody Book book){
        return mongodbService.saveBook(book);
    }

    @GetMapping("/findAll")
    public List<Book> findAll(){
        return mongodbService.findAll();
    }

    @GetMapping("/findOneById")
    public Book findOneById(@RequestParam String id){
        return mongodbService.findBookById(id);
    }

    @PostMapping("/update")
    public String update(@RequestBody Book book){
        return mongodbService.updateBook(book);
    }

    @PostMapping("/del")
    public String delBook(@RequestBody Book book){
        return mongodbService.deleteBook(book);
    }

}
