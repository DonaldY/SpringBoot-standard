package com.donaldy;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author donald
 * @date 2023/6/4
 */
public class DigestUtilsExample {
    public static void main(String[] args) {
        String str = "Hello, World!";
        String md5 = DigestUtils.md5Hex(str);
        System.out.println(md5);
    }
}
