package com.example.demoapp.thread;

import lombok.SneakyThrows;

import static javafx.scene.input.KeyCode.J;

/**
 * 通过一个锁和一个状态变量来实现
 *
 */
public class JtPrintABC_StateLock implements Runnable {
    //状态变量
    private volatile int state = 0;
    private static final int PRINT_COUNT = 10;
    //打印锁
    private Object printLock;
    ////打印标识位 和state变量相关
    private int printFlag;
    //下一个线程的打印标识位，state变量相关
    private int nextPrintFlag;
    //打印字符
    private String print_str;

    public JtPrintABC_StateLock(Object printLock, int printFlag, int nextPrintFlag, String print_str) {
        this.printLock = printLock;
        this.printFlag = printFlag;
        this.nextPrintFlag = nextPrintFlag;
        this.print_str = print_str;
    }

    @SneakyThrows
    @Override
    public void run() {
        //获取打印锁 进入临界区
        synchronized (printLock){
            //连续打印PRINT_COUNT次
            for (int i = 0; i < PRINT_COUNT; i++) {
                //循环检验标识位 每次都阻塞然后等待唤醒
                while (state != printFlag){
                    printLock.wait();
                }
                //打印字符
                System.out.print(print_str);
                //设置状态变量为下一个线程的标识位
                state = nextPrintFlag;
                //注意要notifyall，不然会死锁，因为notify只通知一个，
                //但是同时等待的是两个,如果唤醒的不是正确那个就会没人唤醒，死锁了
                printLock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        JtPrintABC_StateLock pa = new JtPrintABC_StateLock(object,0,1,"A");
        JtPrintABC_StateLock pb = new JtPrintABC_StateLock(object,1,2,"B");
        JtPrintABC_StateLock pc = new JtPrintABC_StateLock(object,2,0,"C");

        new Thread(pa).start();
        Thread.sleep(1000);
        new Thread(pb).start();
        Thread.sleep(1000);
        new Thread(pc).start();
        //Thread.sleep(100);

    }
}
