package com.donaldy.file;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author donald
 * @date 2024/12/12
 */
public class ScanFileTest {
    public static void main(String[] args) {
        String projectPath = "/Users/yangyf/Documents/PingPong/Code/bak/sss";
        String outputPath = "./translation.txt";

        Set<String> extractedStrings = scanJavaFiles(projectPath);
        List<String> sortedStrings = new ArrayList<>(extractedStrings);
        Collections.sort(sortedStrings);
        writeToFile(sortedStrings, outputPath);

        System.out.println("Extraction complete. Results written to: " + outputPath);
    }

    public static Set<String> scanJavaFiles(String projectPath) {
        Set<String> results = new HashSet<>(); // Using Set for automatic deduplication
        File projectDir = new File(projectPath);
        scanDirectory(projectDir, results);
        return results;
    }

    private static void scanDirectory(File directory, Set<String> results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanDirectory(file, results);
                } else if (file.getName().endsWith(".java")) {
                    scanJavaFile(file, results);
                }
            }
        }
    }

    private static void scanJavaFile(File file, Set<String> results) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getPath())));

            // Pattern 1: Assert.errorIsTrue
            addMatches(content, "Assert\\.errorIsTrue\\([^,]+,\\s*\"([^\"]*)\"\\)", results);

            // Pattern 2: throw new RuntimeException
            addMatches(content, "throw new RuntimeException\\(\"([^\"]*)\"\\)", results);

            // Pattern 3: throw new BizException
            addMatches(content, "throw new BizException\\([^,]+,\\s*\"([^\"]*)\"\\)", results);

            addMatches(content, "Assert\\.warnIsTrue\\([^,]+,\\s*\"([^\"]*)\"\\)", results);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addMatches(String content, String regex, Set<String> results) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            results.add(matcher.group(1));
        }
    }

    private static void writeToFile(List<String> strings, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String str : strings) {
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Set<String> results = new HashSet<>();

        addMatches("Assert.errorIsTrue(StringUtils.isNotBlank(clientId), \"ClientId Can't Be Null\");", "Assert\\.errorIsTrue\\([^,]+,\\s*\"([^\"]*)\"\\)", results);

        // Pattern 2: throw new RuntimeException
        addMatches("throw new RuntimeException(\"transfer failed.\");", "throw new RuntimeException\\(\"([^\"]*)\"\\)", results);

        // Pattern 3: throw new BizException
        addMatches("throw new BizException(ErrorCode.PARAM_ERROR, \"input resource is null.\");", "throw new BizException\\([^,]+,\\s*\"([^\"]*)\"\\)", results);

        addMatches("Assert.warnIsTrue(true, \"店铺编号、SellerId、客户编号、店铺链接、归属人需填其一\");", "Assert\\.warnIsTrue\\([^,]+,\\s*\"([^\"]*)\"\\)", results);
        System.out.println(results);
    }
}
