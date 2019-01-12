package com.donaldy.unit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class StreamTest {

    public static Stream<Integer> generate(int starting) {

        System.out.println(123);
        return null;
    }

    public static void main(String[] args) {

        // System.out.println(Arrays.toString(generate(20).toArray()));

        // 1
        /*System.out.println(test("bbbbb"));

        // 3
        System.out.println(test("abc"));

        // 3
        System.out.println(test("abcabcabc"));
*/
        // 3
        System.out.println(test("pwwkew"));

        /*// 6
        System.out.println(test("bbtablud"));*/
    }

    public static int test(String str) {

        int count = 0;

        int i = 0;

        String test = "";

        int temp = 0;

        char [] arr = str.toCharArray();

        for (int j = 0; j < arr.length; ++j) {

            // System.out.println("out : " + test);

            if (test.indexOf(arr[j] + "") < 0) {

                //System.out.println("j : " + test.indexOf(arr[j] +""));

                test += arr[j];

                //System.out.println("test : " + test);

                temp = test.length();

                if (count < temp) {
                    count = temp;
                }

                continue;
            }

            ;

            test = str.substring(i - 1, j);

            temp = 0;
        }

        return count;
    }
}
