package com.donaldy.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author donald
 * @date 2023/5/30
 */
public class MD5Example {
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String timestamp = "2023-07-02 16:00:00";
        String key = "0c038af6bfbeb7277e40e3f9c0ec87fd";
        String secret = "10f1860a63aa07e924475d6057d84289";
        String md5 = getMD5(key + secret + timestamp );
        System.out.println(md5);

        System.out.println(getMD5("123"));


        System.out.println(executeResId("在开车"));
        System.out.println(executeClientId());
    }

    private static String executeResId(String resId) {
        Pattern pattern = Pattern.compile("\\d+");

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(resId);

        // 逐个查找匹配的数字
        if (matcher.find()) {
            return matcher.group();
        }
        return resId;
    }

    public static String executeClientId() {
//        String str = "liuyf1@pingpongx.com已经开通了，已经用了三四年";
        String str = "liuyf1@pingpongx.com";
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[\\w.-]+\\.[A-Za-z]{2,6}");

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(str);

        // 查找匹配的邮箱
        if (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
            return match;
        } else {
            System.out.println("未匹配到邮箱");
        }
        return "";
    }
}
