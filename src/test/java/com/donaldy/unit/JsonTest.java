package com.donaldy.unit;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class JsonTest {
    public static void main(String[] args) throws IOException {
        Set<String> set = new HashSet<>();
        String str1 = getStr("/Users/yangyf/Documents/Code/java/SpringBoot-standard/src/main/resources/test.json");
        execute(set, str1);
        String str2 = getStr("/Users/yangyf/Documents/Code/java/SpringBoot-standard/src/main/resources/test2.json");
        execute(set, str2);
        System.out.println("final set size: " + set.size());
    }

    static String getStr(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);

        return IOUtils.toString(fis, "UTF-8");
    }

    static void execute(Set<String> set, String str) {
        Response response = JSON.parseObject(str, Response.class);
        List<Map<String, String>> list = response.getData().getTradeList();
        System.out.println("list size: " + list.size());
        for (Map<String, String> map : list) {
            set.add(map.getOrDefault("tradeId", ""));
        }
        System.out.println("set size: " + set.size());
    }
}
@Data
class Response {
    private Integer code;
    private ObjData data;
}

@Data
class ObjData {
    private Integer total;
    private List<Map<String, String>> tradeList;
}