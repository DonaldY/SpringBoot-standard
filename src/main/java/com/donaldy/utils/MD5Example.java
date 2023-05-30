package com.donaldy.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        String timestamp = "2023-05-30 14:05:00";
        String key = "0c038af6bfbeb7277e40e3f9c0ec87fd";
        String secret = "10f1860a63aa07e924475d6057d84289";
        String md5 = getMD5(key + secret + timestamp );
        System.out.println(md5);
    }
}
