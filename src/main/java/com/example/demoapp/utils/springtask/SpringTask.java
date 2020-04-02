package com.example.demoapp.utils.springtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SpringTask {

    /**
     * 每隔5s执行一次，{秒} {分} {时} {日期} {月} {星期}
     */
    @Scheduled(cron = "1/5 * * * * *")
    public void testOne(){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 延迟1s,每隔5S执行一次
     */
    @Scheduled(initialDelay = 1000,fixedRate = 5000)
    public void testTow(){
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
