package com.example.demoapp.controller;

import com.example.demoapp.customannotation.AspectAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@RestController
@RequestMapping(value = "/aspcontr")
public class AspectController {

    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public Object firstContr(){
        return "---- first controller execute---";
    }

    @RequestMapping("/second")
    @AspectAnnotation(desc = "second")
    public Object secondContr(){
        return "-----second controller----";
    }

    @RequestMapping("/error")
    public Object doError(){
        return 1/0;
    }

}
