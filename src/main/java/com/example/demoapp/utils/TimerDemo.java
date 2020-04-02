package com.example.demoapp.utils;

import com.example.demoapp.utils.springtask.SpringTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java开发过程中经常会遇到使用定时任务的情况，我总结了一下有如下
 * 四种方式：Timer、ScheduledExecutorService、SpringTask、Quartz。
 */
public class TimerDemo {

    //时间间隔：24小时
    private final long periodTime = 24*60*60*1000;

    @Autowired
    private SpringTask springTask;

    public static void timerBySleep(){
        final int timerTime = 3000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println(Thread.currentThread().getName()+"定时间隔3秒");
                        Thread.sleep(timerTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread().start();
    }

    /**
     * Timer TimerTask
     * Timer 的优点在于简单易用，但由于所有任务都是由同一个线程来调度，因此所有任务都是串行执行的，
     * 同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务（这点需要注意）。
     */
    public static void testTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":定时间隔3秒");
            }
        };
        Timer timer = new Timer();
        long delay = 1000;
        long intevalTime = 3000;
        //delay:延迟 period: 一段时间间隔(定时时间)
        //timer.schedule(timerTask,delay);
        timer.scheduleAtFixedRate(timerTask,delay,intevalTime);
    }

    public static void testTimer_t(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse("2020-04-03 12:00:00");
            new Timer("timerdemo").scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                }
            },date,3000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用ScheduledExecutorService接口
     * 延迟1s启动，每隔5s执行一次，是前一个任务开始时就开始计算时间间隔，
     * 但是会等上一个任务结束在开始下一个
     * scheduleAtFixedRate()
     */
    public static void testScheduledExecutorService(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
               System.out.println("dsfas.....");
            }
        },1,5, TimeUnit.SECONDS);
    }

    /**
     * 延迟1s启动，在前一个任务执行完成之后，延迟1s在执行
     *  scheduleWithFixedDelay()
     */
    public static void testSchduledExecutorServiceT(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },1,5,TimeUnit.SECONDS);
    }

    public void testSpringTask(){
        springTask.testOne();
    }
    public void testSpringTaskT(){
        springTask.testTow();
    }

    public static void main(String[] args) {
        //testTimer();
        //testScheduledExecutorService();
        //testSchduledExecutorServiceT();
        TimerDemo timerDemo = new TimerDemo();
        timerDemo.testSpringTask();
    }
}
