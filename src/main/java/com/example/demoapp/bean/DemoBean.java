package com.example.demoapp.bean;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class DemoBean {

    public String randUUID(long time){
        try{
            System.out.println("in randUUID before porcess...");
            return UUID.randomUUID() +"|"+time;
        }finally {
            System.out.print("in randUUID finally！！！");
        }
    }

}
