package com.donaldy.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author donald
 * @date 2024/12/17
 */
public class TransTest {

    public static void main(String[] args) {
        String inputFile = "/Users/yangyf/Documents/Code/java/SpringBoot-standard/translation.txt";
        String outputFile = "./output.txt";

        AtomicInteger counter = new AtomicInteger(10000);

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            for (String line : lines) {
                String formattedLine = String.format("DEFAULT_CODE_%d(%d, \"%s\"),\n",
                        counter.get(), counter.get(), line.trim());
                writer.write(formattedLine);
                counter.incrementAndGet();
            }

            writer.close();
            System.out.println("处理完成，输出文件：" + outputFile);
        } catch (IOException e) {
            System.err.println("处理文件时发生错误：" + e.getMessage());
        }
    }
}
