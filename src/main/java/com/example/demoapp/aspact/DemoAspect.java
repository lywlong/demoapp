package com.example.demoapp.aspact;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {

    protected void aspectMethod(){
        System.out.println("-----aspect method process...");
    }

    @Before("execution(public * com.example.demoapp.bean.*.*(*))")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("do in Aspect before method called! args:"+ JSON.toJSONString(joinPoint.getArgs()));
    }

    @After("point()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("do in Aspect after method called! args:"+JSON.toJSONString(joinPoint.getArgs()));
    }
    @Pointcut("execution(public * com.example.demoapp.bean.*.*(*))")
    public void point(){

    }

    @AfterReturning(value = "point()&&args(time)",returning = "result")
    public void doAfterReturning(long time,String result){
        System.out.println("do in Aspect after method return! args:"+time+"retStr:"+result);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("do in Aspect around ----- before");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("do in Aspect around----over ! retStr :"+obj);
        return obj;
    }

}
