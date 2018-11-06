package com.donaldy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(public * com.donaldy.controller.UserController.getUser(..))")
    public void time() {

    }

    @Before("time()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("enter into time()");
    }

    @After("time()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("exit time()");
    }
}
