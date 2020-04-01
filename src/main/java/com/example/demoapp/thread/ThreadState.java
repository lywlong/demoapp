package com.example.demoapp.thread;

public class ThreadState implements Runnable {

    public synchronized void waitForASecond() throws InterruptedException {
        wait(500);
    }

    public synchronized void waitForYears() throws InterruptedException {
        wait();
    }

    public synchronized void notifyNow(){
        notify();
    }

    @Override
    public void run() {
        try{
            waitForASecond();
            waitForYears();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
