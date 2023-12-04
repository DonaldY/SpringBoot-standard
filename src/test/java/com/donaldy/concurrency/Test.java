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
        String sql = "INSERT INTO translation (tenantId, code, lang, translation) VALUES ('";
        for (Map<String, String> map : listMap) {
            String tenantId = "flowmore";
            String code = map.getOrDefault("query", "");
            String lang = map.getOrDefault("targetLang", "");
            String translation = map.getOrDefault("target", "");
            String realSql = sql + tenantId + "', '" + code + "', '" + lang + "', '" + translation + "')";
            System.out.println(realSql);
        }
        // System.out.println(listMap.size());
    }

    static String getStr(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);

        return IOUtils.toString(fis, "UTF-8");
    }
}
