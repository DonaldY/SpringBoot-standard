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
public class BuyAspect {
    @Pointcut("execution(public * com.donaldy.service.impl.UserServiceImpl.executeUserInfo(..))")
    public void buy() {

    }

    @Before("buy()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("enter into buy()");
    }

    @After("buy()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("exit buy()");
    }
}
