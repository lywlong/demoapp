package com.example.demoapp;

import com.example.demoapp.bean.DemoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoappApplication {

    public DemoappApplication(DemoBean demoBean) {
        String retStr = demoBean.randUUID(System.currentTimeMillis());
        System.out.println("-----return str :"+retStr+"-----");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoappApplication.class, args);
    }

}
