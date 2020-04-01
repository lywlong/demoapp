package com.example.demoapp.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * 定义Restful接口返回类，定义需要返回的数据的字段
 */
public class ReturnResultUtil implements Serializable {

    private static final Long serialVersionUID = 1L;

    private int code;
    private String msg;
    private Object object;

    public ReturnResultUtil(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnResultUtil(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public static ReturnResultUtil publicOkResp(){
        return new ReturnResultUtil(ResultCode.SUCCESS.code(),ResultCode.SUCCESS.name());
    }

    public static ReturnResultUtil publicOkResp(Object o){
        return new ReturnResultUtil(ResultCode.SUCCESS.code(),ResultCode.SUCCESS.name(),o);
    }

    public static ReturnResultUtil publicCustomMsgResp(ResultCode resultCode,String message){
        return new ReturnResultUtil(resultCode.code(),message);
    }

    public static ReturnResultUtil publicByDataResp(Object o){
        return new ReturnResultUtil(ResultCode.SUCCESS.code(),ResultCode.SUCCESS.name(),o);
    }

    public static ReturnResultUtil publicErrorResp(ResultCode resultCode){
        return new ReturnResultUtil(resultCode.code(),resultCode.name());
    }

    public static ReturnResultUtil publicCustomErrorResp(ResultCode resultCode,String message){
        return new ReturnResultUtil(resultCode.code(),message);
    }

    public static ReturnResultUtil error(ResultCode code,  Object o) {
        return new ReturnResultUtil(code.code(), code.name(), o);
    }

    public static ReturnResultUtil error(BindingResult bindingResult, MessageSource messageSource){
        //创建一个缓冲流
        StringBuffer msgStringBuffer = new StringBuffer();
        //获取错误字段集合
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        //获取本地locale,zh_CN
        Locale locale = LocaleContextHolder.getLocale();
        //
        for (FieldError fieldError:fieldErrors) {
            //获取错误信息
            String errorMessage = messageSource.getMessage(fieldError,locale);
            //添加到错误信息集合内（即上面创建的缓冲流）
            msgStringBuffer.append(fieldError.getField()+":"+errorMessage+",");

        }
        return publicCustomErrorResp(ResultCode.PARAM_ERROR,msgStringBuffer.toString());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "{"+"\"code\""+":"+code+","+"\"msg\""+":"+msg+","+"\"Object\""+":"+object+"}";
    }
}
