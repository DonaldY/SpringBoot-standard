package com.donaldy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Slf4j
@EnableAsync
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
     *
     *
     */

    @GetMapping(value = "/deferred")
    public DeferredResult<String> DeferredWay() {
        log.info("enter deferred");
        DeferredResult<String> result = new DeferredResult<>();
        result.setResult("hello deferred");
        log.info("exit deferred");
        return result;
    }

    @GetMapping(value = "/callable")
    public Callable<String> callableWay() {
        log.info("enter callable");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("fuck you");
                return "hello yyf";
            }
        };
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

    @GetMapping(value = "/test1")
    public String test1() {
        log.info("进入 test1");
        String str = getTest1();
        System.out.println(str);
        log.info("str : {} ", str);
        return "test1 finished!";
    }

    @Async
    public String getTest1() {
        log.info("get test ");
        return "success";
    }

}
