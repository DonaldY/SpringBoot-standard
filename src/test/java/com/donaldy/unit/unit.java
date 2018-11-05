package com.donaldy.unit;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class unit {

    @Test
    public void testCalendarBig() {

        int monthInterval = this.getMonthBetweenTwoDate("2018-10", "2017-10");

        Assert.assertEquals("", 12, monthInterval);
    }

    @Test
    public void testCalendarMiddleBig() {

        int monthInterval = this.getMonthBetweenTwoDate("2018-11", "2018-10");

        Assert.assertEquals("", 1, monthInterval);
    }

    @Test
    public void testCalendarMiddleSmall() {

        int monthInterval = this.getMonthBetweenTwoDate("2018-10", "2018-12");

        Assert.assertEquals("", 2, monthInterval);
    }

    @Test
    public void testCalendarSmall() {
        int monthInterval = this.getMonthBetweenTwoDate("2017-09", "2018-10");

        Assert.assertEquals("", 13, monthInterval);
    }

    @Test
    public void testCalendarEqual() {
        int monthInterval = this.getMonthBetweenTwoDate("2018-11", "2018-11");

        Assert.assertEquals("", 0, monthInterval);
    }

    private int getMonthBetweenTwoDate(String nowDateStr, String originalDateStr) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM");

        try {

            Date originalDate = sdf.parse(originalDateStr);

            Date nowDate= sdf.parse(nowDateStr);

            Calendar c1 = Calendar.getInstance();

            Calendar c2 = Calendar.getInstance();

            c1.setTime(originalDate);

            c2.setTime(nowDate);

            int year1 = c1.get(Calendar.YEAR);
            int year2 = c2.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH);
            int month2 = c2.get(Calendar.MONTH);

            int yearInterval = Math.abs(year1 - year2);

            int monthInterval = 0;

            if (month1 < month2) {
                yearInterval --;
                monthInterval = month1 + 12 - month2;
            } else {
                monthInterval = month1 - month2;
            }

            return Math.abs(yearInterval * 12 + monthInterval);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
