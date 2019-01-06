package com.donaldy.unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class LambdaTest {

    public static void main(String[] args) {

        /*boolean flag = true;

        Callable<Integer> c = flag ? (() -> 24) : (() -> 42);

        System.out.println();*/

        /*String [] texts = {"芦花丛中一扁舟", "俊杰俄从此地游", "义士若能知此理", "反躬难逃可无忧"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texts.length; ++i) {
            sb.append(texts[i].substring(0, 1));
        }

        System.out.println(sb.toString());*/

        /*List<String> texts = Arrays.asList("芦花丛中一扁舟", "俊杰俄从此地游", "义士若能知此理", "反躬难逃可无忧");
        Optional optional = texts.stream().map(s -> (s.substring(0, 1))).reduce((result, item) -> (result + item));
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }*/

        String country;

        List<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("New York");
        cities.add("Beijing");
        cities.add("1kjh1231");
        cities.add("Beijing");

        country = cities.stream()
                .filter( c -> c.equals("Beijing"))
                .findAny()
                .map(v -> "China")
                .orElse(null);
        System.out.println(country);
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
