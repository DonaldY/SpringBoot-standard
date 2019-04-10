package com.donaldy.unit;

import org.hashids.Hashids;

public class test {

    public static void main(String[] args) {

        Hashids hashids = new Hashids("Donald", 10);

        System.out.println(hashids.encode(221432423543535L));
    }
}
