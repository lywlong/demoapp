package com.example.demoapp.thread;

public class ThreadTest {

    public static void main(String[] args) {

        test_t();

    }

    public static void test_t(){
        Thread thread = new Thread(new Transfer(new Bank()));
        thread.start();
        Thread thread2 = new Thread(new Transfer(new Bank()));
        thread2.start();
    }

    public static void test() throws InterruptedException {
        ThreadState threadState = new ThreadState();
        Thread t = new Thread(threadState);
        System.out.println("新建线程："+t.getState());
        t.start();
        System.out.println("启动线程："+t.getState());
        t.sleep(1000);
        System.out.println("计时等待："+t.getState());
        t.sleep(2000);
        System.out.println("等待线程："+t.getState());
        threadState.notifyNow();
        System.out.println("唤醒线程："+t.getState());
        t.sleep(1000);
        System.out.println("终止线程："+t.getState());
    }

}
