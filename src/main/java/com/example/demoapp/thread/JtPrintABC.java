package com.example.demoapp.thread;

public class JtPrintABC implements Runnable {

    private String charName;
    private Object prev;
    private Object self;

    public JtPrintABC(String charName, Object prev, Object self) {
        this.charName = charName;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            //每次操作两个线程对象
            //判断进来的线程对象有没有锁
            //同步方法块，锁是括号里面的对象，对给定对象加锁，
            // 进入同步代码库前要获得给定对象的锁。
            synchronized (prev){
                synchronized (self){
                    System.out.print(charName);
                    count--;
                    // 通过本线程的打印锁唤醒后面的线程
                    // notify和notifyall均可,因为同一时刻只有一个线程在等待
                    self.notify();
                }

                try{
                    prev.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void test(){
        new Thread(){
            @Override
            public void run() {
                //写入线程执行的代码
            }
        }.start();
    }
    //jdk1.8
    public void test_tow(){
        new Thread(()->{
                    //写入线程执行的代码
        }).start();
    }


    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        JtPrintABC jt1 = new JtPrintABC("A",c,a);//线程a
        JtPrintABC jt2 = new JtPrintABC("B",a,b);//线程b
        JtPrintABC jt3 = new JtPrintABC("C",b,c);//线程c

        /*
         *   交替打印ABC 10次。
         *   使用synchronized同步锁，创建建三个线程对象，打印ABC的顺序为：
         *   A(线程a,获得两个对象锁（c,a）a.notify(),c.wait()（打印A后使当前线程暂停，交出锁）)
         *   ->B(线程b,获得两个对象锁(a,b) b.notify(),a.wait()（打印B后使当前线程暂停，交出锁）)
         *   ->C(线程a,获得两个对象锁(b,c) c.notify(),b.wait()（打印C后使当前线程暂停，交出锁）)
         *   注：wait()与notify()两个方法成对使用，同一个对象相互调用，实现对一个线程状态的改变。
         *   控制顺序的关键点是new Thread(jt1).start()运行后，调用sleep()方法，短暂睡眠，在这短暂时间
         *   时间内，两个对象锁自然是线程a的。
         */

        new Thread(jt1).start();
        Thread.sleep(100);// 确保按顺序A、B、C执行（这一步实现顺序的关键）
        new Thread(jt2).start();
        Thread.sleep(100);
        new Thread(jt3).start();
        Thread.sleep(100);
    }
}
