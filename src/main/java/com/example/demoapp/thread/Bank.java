package com.example.demoapp.thread;

public class Bank {

    private int account = 100;
    public synchronized void deposit(int money){
        account += money;
    }

    public int getAccount(){
        return account;
    }
}
