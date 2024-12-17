package com.donaldy.file;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author donald
 * @date 2024/12/17
 */
public class ExtractErrorCode {
    public static void main(String[] args) {
        String inputFilePath = "/Users/yangyf/Documents/PingPong/Code/bak/sss/sales-common/src/main/java/com/pingpongx/smb/sales/common/exception/ErrorCode.java";
        String outputFilePath = "./file.txt";

        try {
            // 读取输入文件
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // 定义正则表达式
            Pattern pattern = Pattern.compile("\\b\\w+\\((\\d+),\\s*\"([^\"]*)\"\\)");
            Matcher matcher = pattern.matcher(content.toString());

            // 创建输出文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            // 匹配并写入输出文件
            while (matcher.find()) {
                String errorCode = matcher.group(1);
                String errorMessage = matcher.group(2);
                writer.write(errorCode + "=" + errorMessage);
                writer.newLine();
            }

            writer.close();
            System.out.println("提取完成,已输出到 " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
