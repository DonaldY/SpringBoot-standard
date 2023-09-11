package com.donaldy.concurrency;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author donald
 * @date 2023/8/31
 */
public class Test {

    @org.junit.Test
    public void test3() throws IOException {
        String json = getStr("/Users/yangyf/Documents/Code/java/SpringBoot-standard/src/main/resources/test.json");
        List<Map<String, String>> listMap = JSONObject.parseObject(json, new TypeReference<List<Map<String, String>>>() {});
        for (Map<String, String> map : listMap) {
            System.out.println(map.getOrDefault("companyName", ""));
//            System.out.println(map.getOrDefault("bossId", ""));
        }
    }

    static String getStr(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);

        return IOUtils.toString(fis, "UTF-8");
    }
}
