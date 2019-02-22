package com.donaldy.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUtils {

    private ExecutorServiceUtils(){}

    public static ExecutorService getInstance() {
        return ExecutorServiceUtils.ExecutorEnum.INSTANCE.getInstance();
    }

    private enum ExecutorEnum {
        INSTANCE;

        private ExecutorService instance;

        ExecutorEnum() {
            instance = Executors.newFixedThreadPool(2);
        }

        public ExecutorService getInstance() {
            return this.instance;
        }
    }
}
