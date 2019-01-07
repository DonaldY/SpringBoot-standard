package com.donaldy.unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        /*String country;

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
        System.out.println(country);*/

        LambdaTest lambdaTest = new LambdaTest();

        // lambdaTest.testPredicate();

        // lambdaTest.testMap();

        // lambdaTest.testReduce();

        lambdaTest.testStringList();
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

    public void testPredicate() {

        List<String> languages = Arrays.asList("Java", "JavaScript", "Scala", "C++", "Haskell");

        System.out.println("Language which starts with J: ");
        filter(languages, (str) -> str.startsWith("J"));

        // OR after
        filter2(languages, (str) -> str.startsWith("J"));

        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        languages.stream().filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("Name, which starts with 'J' and four letter long is : " + n ));

    }

    private void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name);
            }
        }
    }

    private void filter2 (List < String > names, Predicate < String > condition){
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    public void testMap() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        costBeforeTax.stream().map((cost) -> cost + .12 * cost)
                .forEach(System.out::println);
    }

    public void testReduce() {

        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }

        System.out.println("Total : " + total);

        // 之后
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println("Bill : " + bill);
    }

    public void testStringList() {

        List<String> strList = Arrays.asList("English", "Chinese", "French", "KaKoa");

        List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());

        System.out.printf("Original List : %s, filtered list : %s", strList, filtered);
    }
}
