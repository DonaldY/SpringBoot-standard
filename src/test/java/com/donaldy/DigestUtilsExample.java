package com.donaldy;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author donald
 * @date 2023/6/4
 */
public class DigestUtilsExample {
    public static void main(String[] args) {
//        String sha1 = DigestUtils.sha1Hex(str); // 计算SHA-1散列值
//        String sha256 = DigestUtils.sha256Hex(str); // 计算SHA-256散列值
//        String sha384 = DigestUtils.sha384Hex(str); // 计算SHA-384散列值
//        String sha512 = DigestUtils.sha512Hex(str); // 计算SHA-512散列值
//        String md5 = DigestUtils.md5Hex(str);
        String email = "luchenhui@udesk.cn";
        String key = "5e444e7e33574139afcc6f67e0fa960a";
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        String str = DigestUtils.sha1Hex(email + "&" + key + "&" + timestamp);
        System.out.println(str);
    }
}
