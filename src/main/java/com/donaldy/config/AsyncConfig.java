package com.donaldy.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.setTaskDecorator(MdcTaskDecorator::new);
        executor.initialize();
        return executor;
    }

    // 自定义的Runnable装饰器，用于设置MDC
    private static class MdcTaskDecorator implements Runnable {

        private final Runnable delegate;

        public MdcTaskDecorator(Runnable delegate) {
            this.delegate = delegate;
        }

        @Override
        public void run() {
            String traceId = MDC.get("traceId");
            if (StringUtils.isNotBlank(traceId)) {
                MDC.put("traceId", traceId);
            }
            try {
                delegate.run();
            } finally {

                MDC.clear();
            }
        }
    }
}