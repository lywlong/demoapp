package com.example.demoapp.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 仔细想想本问题，既然同一时刻只能有一个线程打印字符，那我们为什么不使用一个
 * 同步锁ReentrantLock？线程之间的唤醒操作可以通过Condition实现，且Condition可以有多个，
 * 每个condition.await阻塞只能通过该condition的signal/signalall来唤醒！这是synchronized关键字
 * 所达不到的，那我们就可以给每个打印线程一个自身的condition和下一个线程的condition，
 * 每次打印字符后，调用下一个线程的condition.signal来唤醒下一个线程，然后自身再通过自己的
 * condition.await来释放锁并等待唤醒。
 */
public class JtPrintABC_ReentrantLock implements Runnable {

    private static final int PRINT_COUNT = 10;
    //打印锁
    private final ReentrantLock reentrantLock;
    //当前线程打印条件
    private Condition concurrent_condition;
    //下一个线程打印执行条件
    private Condition next_condition;
    private String print_str;

    public JtPrintABC_ReentrantLock(ReentrantLock reentrantLock, Condition concurrent_condition, Condition next_condition, String print_str) {
        this.reentrantLock = reentrantLock;
        this.concurrent_condition = concurrent_condition;
        this.next_condition = next_condition;
        this.print_str = print_str;
    }

    @SneakyThrows
    @Override
    public void run() {
        //获取打印锁，进入临界区
        reentrantLock.lock();
        for (int i = 0; i < PRINT_COUNT; i++) {
            //打印字符
            System.out.print(print_str);
            next_condition.signal();
            // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
            if(i < PRINT_COUNT-1){
                //当前线程让出锁，并等待唤醒
                concurrent_condition.await();
            }
        }
        //释放打印锁
        reentrantLock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        //创建锁
        ReentrantLock reentrantLock = new ReentrantLock();
        //创建打印每个字符的条件
        Condition con_a = reentrantLock.newCondition();
        Condition con_b = reentrantLock.newCondition();
        Condition con_c = reentrantLock.newCondition();

        JtPrintABC_ReentrantLock jta = new JtPrintABC_ReentrantLock(reentrantLock,con_a,con_b,"A");
        JtPrintABC_ReentrantLock jtb = new JtPrintABC_ReentrantLock(reentrantLock,con_b,con_c,"B");
        JtPrintABC_ReentrantLock jtc = new JtPrintABC_ReentrantLock(reentrantLock,con_c,con_a,"C");

        new Thread(jta).start();
        Thread.sleep(100);
        new Thread(jtb).start();
        Thread.sleep(100);
        new Thread(jtc).start();
        Thread.sleep(100);
    }
}
