package com.example.demoapp.thread;

public class Transfer implements Runnable {

    private Bank bank;

    public Transfer(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bank.deposit(10);
            System.out.println("账户的余额是："+bank.getAccount());
        }
    }
}
