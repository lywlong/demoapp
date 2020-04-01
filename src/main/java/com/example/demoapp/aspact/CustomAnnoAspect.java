package com.example.demoapp.aspact;

import com.example.demoapp.customannotation.AspectAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAnnoAspect {

    @Pointcut(value = "@annotation(com.example.demoapp.customannotation.AspectAnnotation)")
    public void customAnno(){

    }

    @Before("customAnno()")
    public void doBefore(){
        System.out.println("--second before");
    }

    //主要看一下@Around注解这里，如果需要获取在controller注解中赋给AspectAnnotation的desc里的值，
    // 就需要这种写法，这样AspectAnnotation参数就有值了。
    @Around("@annotation(aspectAnnotation)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, AspectAnnotation aspectAnnotation) throws Throwable {
        System.out.println("，，，环绕开始。。。。"+aspectAnnotation.desc());
        Object obj = proceedingJoinPoint.proceed();
        return obj;
    }


}
