package com.donaldy.unit;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class LambdaTest {

    public static void main(String[] args) {

        boolean flag = true;

        Callable<Integer> c = flag ? (() -> 24) : (() -> 42);

        System.out.println();

    }

    public void testRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("not use lambda");
            }
        }).start();

        new Thread( () -> System.out.println("use lambda")).start();
    }

    public void testCollection() {

        List<String> features = Arrays.asList("lambdas", "default", "stream api");

        // 之前：
        for (String feature : features) {
            System.out.println(feature);
        }

        // lambda 之后：
        features.forEach(item -> System.out.println(item));

        // 更简洁：
        features.forEach(System.out::println);
    }


}
