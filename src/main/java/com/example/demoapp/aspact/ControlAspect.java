package com.example.demoapp.aspact;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class ControlAspect {

    //定义通用切点
    @Pointcut("execution(* com.example.demoapp.Aspect*.*.*(..))")
    public void webLog(){

    }

    //before advice
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //在控制台打印请求的内容
        System.out.println("URL:"+request.getRequestURL().toString());
        System.out.println("HTTP_MEHTOD:"+request.getMethod());
        System.out.println("IP:"+request.getRemoteAddr());
        System.out.println("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+"."+
                joinPoint.getSignature().getName());
        System.out.println("ARGS:"+joinPoint.getArgs());
    }

    //执行完切点方法后，返回方法的结果
    @AfterReturning(returning = "result",pointcut = "webLog()")
    public void doAfterReturning(Object result){
        System.out.println("------切点方法执行后返回的结果："+result);
    }

    //后置异常通知
   /* @AfterThrowing("webLog()")
    public void doAfterException(JoinPoint joinPoint){
        System.out.println("方法异常时执行--");
    }*/

    //与上作对比

    @AfterThrowing(throwing = "ex",pointcut = "webLog()")
    @Order
    public void doThrows(Exception ex){
        System.out.println("---方法异常时执行----");
    }

    //后置最终通知，finl增强，不管是抛出异常还是正常执行能出 都会执行。
    @After("webLog()")
    public void doAfter(){
        System.out.println("------方法最后执行。。。");
    }

    //环绕通知，环绕增强，相当于methodInterceptor
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("----方法环绕开始===");
        try{
            Object obj = proceedingJoinPoint.proceed();
            System.out.println("----方法环绕执行proceed(),结果是："+obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }


}
