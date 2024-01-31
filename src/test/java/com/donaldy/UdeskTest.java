package com.donaldy;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author donald
 * @date 2023/9/13
 */
public class UdeskTest {

    @Test
    public void test() {
        String email = "";
        String key = "";
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        String str = DigestUtils.sha1Hex(email + "&" + key + "&" + timestamp);
        System.out.println(str);
    }

    @Test
    public void test2() {
        long tt = 1695011082000L;
        System.out.println(tt);
        System.out.println(Long.MAX_VALUE);
    }

    @Test
    public void test3() {
        String text = "2209300002742224 钻石会员 KYC: Passed";
//        String text = "2209300002742224";
//        String text = "浙江杭州联通(1704848305)";
//        String text = "862401240110825607";

        String extractedText = extractNumber(text);
        System.out.println("Extracted text: " + extractedText);
    }

    private static String extractNumber(String text) {
        String pattern = "^([0-9]+)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}
