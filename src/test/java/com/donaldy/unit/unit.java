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

        int monthInterval = this.getMonthInterval("2018-10", "2017-10");

        Assert.assertEquals("", 12, monthInterval);
    }

    @Test
    public void testCalendarMiddleBig() {

        int monthInterval = this.getMonthInterval("2018-11", "2018-10");

        Assert.assertEquals("", 1, monthInterval);
    }

    @Test
    public void testCalendarEqual() {
        int monthInterval = this.getMonthInterval("2018-11", "2018-11");

        Assert.assertEquals("", 0, monthInterval);
    }

    /**
     * 适用 当前时间 大于 起始时间
     * @param nowDateStr       当前时间字符串
     * @param originalDateStr  起始时间字符串
     * @return                 期数
     */
    private int getMonthInterval(String nowDateStr, String originalDateStr) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM");

        try {

            Date originalDate = sdf.parse(originalDateStr);

            Date nowDate= sdf.parse(nowDateStr);

            Calendar nowCalender = Calendar.getInstance();

            Calendar originalCalender = Calendar.getInstance();

            originalCalender.setTime(originalDate);

            nowCalender.setTime(nowDate);

            int originalYear = originalCalender.get(Calendar.YEAR);
            int nowYear = nowCalender.get(Calendar.YEAR);
            int originalMonth = originalCalender.get(Calendar.MONTH);
            int nowMonth = nowCalender.get(Calendar.MONTH);

            if (nowYear < originalYear || (nowYear == originalYear && nowMonth < originalMonth)) {
                return 0;
            }

            int yearInterval = Math.abs(nowYear - originalYear);

            int monthInterval = 0;

            if (nowMonth < originalMonth) {
                yearInterval --;
                monthInterval = nowMonth + 12 - originalMonth;
            } else {
                monthInterval = nowMonth - originalMonth;
            }

            return Math.abs(yearInterval * 12 + monthInterval);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
