package com.donaldy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author donald
 * @date 2024/7/9
 */
public class TRSignTest {

    public static void main(String[] args) {

        Integer validateType = 2;
        String deptId = "7120356";

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        // 获取东八区当前时间的ZonedDateTime实例
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        // 从ZonedDateTime获取秒级时间戳
        Long timestamp = zonedDateTime.toEpochSecond();
        System.out.println("timestamp: " + timestamp);

        String deptToken = "";

        // MD5({departmentId}+{timestamp}+{部门token值})；
        String sign = getMD5(deptId + timestamp + deptToken);
        System.out.println(sign);
    }

    private static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
