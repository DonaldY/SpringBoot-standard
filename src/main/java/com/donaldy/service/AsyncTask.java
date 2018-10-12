package com.donaldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@Component
public class AsyncTask {
    @Async
    public void dealNoReturnTask(){
        log.info("Thread {} deal No Return Task start", Thread.currentThread().getId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread {} deal No Return Task end at {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @Async
    public Future<String> dealHaveReturnTask(int i) {
        log.info("asyncInvokeReturnFuture, parementer=" + i);
        Future<String> future;
        try {
            Thread.sleep(1000 * i);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }
}