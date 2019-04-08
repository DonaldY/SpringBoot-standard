package com.donaldy.unit;

import java.io.File;

public class FileTest {

    public static void main(String[] args) {

        try {

            // 会创建目录： Java/f/s/f, 这四个目录
            File f = new File("./Java/f/s/f");

            // 创建
            boolean bool = f.mkdirs();

            System.out.print("Directory created? "+bool);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
