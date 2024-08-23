package com.donaldy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author donald
 * @date 2024/8/9
 */
@Slf4j
public class TestTraceId {

    private static final ThreadPoolExecutor DEFAULT_EXECUTOR = new ThreadPoolExecutor(20, 80,
            10L, SECONDS, new LinkedBlockingQueue<>(),
            (r) -> {
                int count = 0;
                return new Thread(r, "Thread-" + (++count));
            });


    public static void main(String[] args) {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);
        System.out.println("traceId: " + traceId);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(runnable -> wrapAsync(runnable, MDC.getCopyOfContextMap()));
        executor.initialize();
        executor.execute(() -> log.info("test log"));

        executor.shutdown();
    }

    public static Runnable wrapAsync(Runnable task, Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            if (MDC.get("traceId") == null) {
                MDC.put("traceId", UUID.randomUUID().toString());
            }
            try {
                task.run();
            } finally {
                MDC.clear();
            }
        };
    }

    public class TraceIdTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            Map<String, String> context = MDC.getCopyOfContextMap();

            return () -> {
                try {
                    MDC.setContextMap(context);

                    runnable.run();
                } finally {

                    MDC.clear();
                }
            };
        }
    }
}
