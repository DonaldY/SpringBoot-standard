package com.donaldy.utils;

import org.hashids.Hashids;

import java.util.Arrays;

public class HashidsUtils {

    private enum HashidsEnum {
        INSTANCE;

        private Hashids instance;

        HashidsEnum() {
            instance = new Hashids("td-object-service");
        }

        public Hashids getInstance() {
            return this.instance;
        }
    }

    private HashidsUtils() {}

    private static Hashids getInstance() {
        return HashidsEnum.INSTANCE.getInstance();
    }

    /**
     * 加密
     * @param fileId 文件ID
     * @return       加密字符串
     */
    public static String encode(Integer fileId) {

        return getInstance().encode(fileId);
    }

    /**
     * 解密
     * @param encryptedString 加密字符串
     * @return                id
     */
    public static int decode(String encryptedString) {

        long [] arr = getInstance().decode(encryptedString);

        return (int) arr[0];
    }

    /**
     * 测试
     * 1. hex 十六进制
     *
     * @param args
     */
    public static void main(String[] args) {
        Hashids hashids = new Hashids("td-object-service");

        String hash = hashids.encode(1350508287623328L);

        //String hash = hashids.encodeHex("12321321321321321312321321");

        System.out.println("encode : " + hash);

        /*long [] arr = hashids.decode("123444");

        System.out.println(Arrays.toString(arr));

        System.out.println("decode : " + arr[0]);*/

        System.out.println("decode : " + Arrays.toString(hashids.decode(hash)));

    }


}
