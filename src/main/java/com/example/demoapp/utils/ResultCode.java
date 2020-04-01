package com.example.demoapp.utils;

public enum ResultCode {

    SUCCESS(200,"success"),
    FAIL(400,"request the page is not exist"),
    UNAUTHORIZED(401,"the request is get ,but is not author"),
    NOT_FOUND(404,"request the page is not exist"),
    INTERNAL_SERVER_ERROR(500,"the server is error"),

    USER_NOT_EXIST(100, "user is not exist"),
    USERNAME_EXIST(101, "username is exist"),
    LOGIN_ERROR(102, "login error"),
    LOGIN_FAIL(103, "username or password error"),
    AUTH_LIMIT(104, "unauthorized "),
    PARAM_ERROR(105, "request param error "),
    PASSWORD_ERROR(106, "password wrong"),

    SYSTEM_CODE_PARAM_MISS(1001, "Request Param Miss"),
    SYSTEM_CODE_PARAM_ERROR(1002, "Request Param ERROR"),
    SYSTEM_CODE_PARAM_NOT_VALID(1003, "Request Param Not Valid"),
    SYSTEM_CODE_BIND_ERROR(1004, "Bind Error"),
    SYSTEM_CODE_CONSTRAINT_VIOLATION(1005, "Hibernate Constraint Violation"),
    SYSTEM_CODE_VALIDATION_ERROR(1006, "Validation ERROR"),
    SYSTEM_CODE_METHOD_NOT_SUPPORT(1007, "Method Not Support"),
    SYSTEM_CODE_MEDIA_NOT_SUPPORT(1008, "Media Not Support"),
    SYSTEM_CODE_SERVICE_ERROR(1009, "Service Error"),
    SYSTEM_CODE_SERVER_ERROR(1010, "Server Error"),
    SYSTEM_CODE_DATA_INTEGRITY_VIOLATION(1011, "Data Integrity Violation"),


    MEDIA_STATUS_ERROR(2001, "Media Reply Error"),
    MEDIA_STATUS_SRC_ERROR(2002, "Media Src Error"),
    MEDIA_STATUS_SRC_CHANGE(2003, "Media Src Change");


    private int code;
    private String reasonPhrase;

    /**
     * Inferred annotations available,full signature. :可推断的注解,完整签名
     * @return
     */
    public int code(){
        return this.code;
    }

    public String reasonPhrase(){
        return this.reasonPhrase;
    }

    ResultCode(int code,String reasonPhrase){
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

}
