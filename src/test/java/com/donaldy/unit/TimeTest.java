package com.donaldy.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = sdf.parse("2019-5-20");

        Date endDate = sdf.parse("2088-9-30");

        System.out.println((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
    }
}
