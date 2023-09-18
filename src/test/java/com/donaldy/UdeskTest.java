package com.donaldy;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

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
}
