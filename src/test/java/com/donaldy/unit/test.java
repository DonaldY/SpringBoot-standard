package com.donaldy.unit;

import org.hashids.Hashids;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {

        Hashids hashids = new Hashids("Donald", 10);

        System.out.println(hashids.encode(221432423543535L));

        System.out.println(Arrays.toString(hashids.decode("B9g7YNw0EX")));
    }
}
