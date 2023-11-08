package com.cxz.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/9 16:23
 */
@Aspect
@Component
public class aopserviceAspect {

//   @Pointcut("execution(* com.cxz.aopdemo.service.AopService.*(..))")//指定方法
   @Pointcut("execution(* com.cxz.aopdemo.service.AopService.test())")//指定方法
    //@Pointcut("execution(* com.cxz.aopdemo.service.*.*(..))")//指定包下
    //@Pointcut("execution(* com..*() )")
    //@Pointcut("execution(* com.cxz.aopdemo.service.Test.*(..))")//指定接口

    public void point(){

    }

    @Before(value = "point()")
    public void before(){
        System.out.println("transaction begin");
    }

    @After(value = "point()")
    public void after(){
        System.out.println("transaction commit");

    }

    @Around("point()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        System.out.println("transaction BBBBB");

        try {
            sw.start(jp.getSignature().getName());
              jp.proceed();
            System.out.println("transaction CCCCC");

        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }





    }
}
