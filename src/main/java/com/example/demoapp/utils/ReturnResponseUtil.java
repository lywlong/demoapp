package com.example.demoapp.utils;

public class ReturnResponseUtil {

    private static final String SUCCESS = "success";

    public static <T> ReturnResultUtil2<T> publicResponse(){
        return new ReturnResultUtil2<T>().setCode(ResultCode.SUCCESS.code()).setMsg(SUCCESS);
    }

    public static <T> ReturnResultUtil2<T> publicResponseByData(T data){
        return new ReturnResultUtil2<T>().setCode(ResultCode.SUCCESS.code()).setMsg(SUCCESS).setData(data);
    }

    public static <T> ReturnResultUtil2<T> publicResponseCustom(int code,String message){
        return new ReturnResultUtil2<T>().setCode(code).setMsg(message);
    }

    public static <T> ReturnResultUtil2<T> publicErrorResponse(){
        return new ReturnResultUtil2<T>().setCode(ResultCode.FAIL);
    }

}
