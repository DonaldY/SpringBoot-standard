package com.donaldy.unit;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest {

    public static Stream<Integer> generate(int starting) {

        System.out.println(123);
        return null;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(generate(20).toArray()));
    }
}
