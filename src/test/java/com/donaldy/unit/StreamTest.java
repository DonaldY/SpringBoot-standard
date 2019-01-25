package com.donaldy.unit;

import java.util.Objects;
import java.util.stream.Stream;

public class StreamTest {

    public static Stream<Integer> generate(int starting) {

        System.out.println(123);
        return null;
    }

    public static void main(String[] args) {

        // HashMap 先根据 hash 判断，再根据 equals
        System.out.println(Objects.hash(1, 2, 2));
        System.out.println(Objects.hash(2, 1, 2));
    }

}
