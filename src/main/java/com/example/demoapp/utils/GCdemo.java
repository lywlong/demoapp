package com.example.demoapp.utils;

public class GCdemo {

    public static void main(String[] args) {
        int _1m = 1024 * 1024;
        byte [] bytes = new byte[_1m];
        //将bytes置为null,即让它成为垃圾
        bytes = null;
        //通知垃圾回收器回收
        System.gc();
    }

}
