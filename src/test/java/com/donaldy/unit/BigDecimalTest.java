package com.donaldy.unit;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void test1() {

        double x = 0.1;
        BigDecimal b1 = new BigDecimal(0.1);
        BigDecimal b2 = new BigDecimal("0.1");
        BigDecimal b3 = new BigDecimal(x);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    @Test
    public void test2() {

        int x = (int) 1023.99999999999999;

        BigDecimal b1 = new BigDecimal(x);
        BigDecimal b2 = new BigDecimal(1023.99999999999);
        BigDecimal b3 = new BigDecimal("1023.9999999999");

        System.out.println(x);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b3.intValue());
        System.out.println(b2.intValue());
    }

    @Test
    public void test3() {

        BigDecimal b1 = new BigDecimal(10240.99999999);

        System.out.println(b1);
    }

    @Test
    public void test4() {

        double d = 0.09;
        BigDecimal bigDecimal=new BigDecimal(d);
        System.out.println(bigDecimal);
        System.out.println(d);
    }
}
