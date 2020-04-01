package com.example.demoapp.utils;

public class ReturnResultUtil2<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public ReturnResultUtil2<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ReturnResultUtil2<T> setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ReturnResultUtil2<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ReturnResultUtil2<T> setData(T data) {
        this.data = data;
        return this;
    }
}
