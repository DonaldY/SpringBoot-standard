package com.donaldy.controller;

import com.donaldy.service.AsyncTask;
import com.donaldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    /**
     * 在Servlet容器中启动异步支持之后，Controller的方法可以通过 DeferredResult 包装返回值来支持异步处理
     *
     * Callable 可以包装任何需要异步支持的返回值。返回值由配置 `TaskExecutor` 执行相应任务并返回结果
     *
     * Callable 和 DeferredResult做的是同样的事情 ：释放容器线程
     *
     * 需要什么样的异步？
     *
     * 主线程尽早处理完并返回信息，而副线程执行剩余的内容。
     *
     */

    @Autowired
    private UserService userService;

    @GetMapping(value = "/deferred")
    public DeferredResult<String> DeferredWay() {
        log.info("enter deferred");
        DeferredResult<String> result = new DeferredResult<>();
        result.setResult("hello deferred");
        log.info("exit deferred");
        return result;
    }

    @GetMapping(value = "/callable")
    public Callable<String> callableWay() throws Exception {
        log.info("enter callable");
        Thread.sleep(2000);
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("fuck you");
                Thread.sleep(3000);
                log.info("123");
                return "hello yyf";
            }
        };
        /*String callableCallStr = callable.call();
        log.info("callable call : {}", callableCallStr);*/
        log.info("exit callable");
        return callable;
    }

    @GetMapping(value = "/callable2")
    public String callableWay2() {
        log.info("enter callable");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("fuck you");
                return "hello yyf";
            }
        };
        log.info("exit callable");
        return "hi ";
    }

    @GetMapping(value = "/callable3")
    public String callableWay3() {
        log.info(String.valueOf(Thread.currentThread().getId()));
        AsyncTask asyncTask = new AsyncTask();
        asyncTask.dealNoReturnTask();
        try {
            log.info("begin to deal other Task!");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("exit callable3");
        return "hi ";
    }

    @GetMapping(value = "/test1")
    public String test1() {
        log.info("enter test1, threadId : {}", Thread.currentThread().getId());

        userService.executeUserInfo(1);
        userService.executeUserInfo(2);
        userService.executeUserInfo(3);

        log.info("exit test1, threadId : {}", Thread.currentThread().getId());

        return "123";
    }

}
