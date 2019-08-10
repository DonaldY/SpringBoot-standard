package com.donaldy.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) throws ParseException {

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = sdf.parse("2019-5-20");

        Date endDate = sdf.parse("2088-9-30");

        System.out.println((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));*/

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = sdf.parse("2020-3-1");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);

        Date date = calendar.getTime();

        String dateString = sdf.format(date);

        System.out.println(dateString);

        System.out.println(sdf.format(startDate));*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date endTime = sdf.parse("2019-5-20");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        String s = sdf2.format(endTime);

        Date date = null;

        try {

            date = sdf.parse(s);

        } catch (ParseException e) {

            return;
        }

        System.out.println(date);
    }


}
