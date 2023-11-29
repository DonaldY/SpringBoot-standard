package com.donaldy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author donald
 * @date 2023/11/24
 */
public class TranslationTest {

    @Test
    public void test() {
        String input = "[va换汇]获取实时汇率为空:{123}->{456}";
        System.out.println("原文本：" + input);

        // 提取 [] 中的数据并输出为 List
        List<String> extractedDataList = extractDataList(input);
        System.out.println("提取的数据列表：" + extractedDataList);

        // 提取文本，保留方括号
        String extractedText = extractText(input);
        System.out.println("提取的文本：" + extractedText);

        String output = fillData("[va换汇]Get real-time exchange rate is empty:{}->{}", extractedDataList);
        System.out.println("返回数据：" + output);
    }

    private static List<String> extractDataList(String input) {
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(input);

        List<String> extractedDataList = new ArrayList<>();
        while (matcher.find()) {
            extractedDataList.add(matcher.group(1));
        }
        return extractedDataList;
    }

    private static String extractText(String input) {
        return input.replaceAll("\\{.*?\\}", "{}");
    }

    @Test
    public void test2() {
        List<String> dataList = Arrays.asList("13323123", "123");
        String input = "用户不存在clientId: {}, msg: {}";

        // 将列表中的数据填充到文本中的 []
        String filledText = fillData(input, dataList);
        System.out.println("填充后的文本：" + filledText);
    }

    private static String fillData(String input, List<String> dataList) {
        String filledText = input;
        for (String data : dataList) {
            filledText = filledText.replaceFirst("\\{}", "{" + data + "}");
        }
        return filledText;
    }

    @Test
    public void test3() {
        String query = "123123";
        System.out.println(query.substring(3));
    }
}
