package ru.integrations.check.condition.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class SaveResult {
    public static void generateFile(ITestContext context){
        StringBuilder text = new StringBuilder();
        String[] groups = context.getIncludedGroups();
        if (groups.length != 0) {
            if (groups.length > 1) {
                for (String group : groups) {
                    text.append(group).append(" ");
                }
            } else {
                text.append(groups[0]).append(" ");
            }
        } else {
            text.append("Not Group").append(" ");
        }
        text.append((context.getFailedTests().size() == 0 && context.getSkippedTests().size() == 0)
                ? "**Test Passed**" : "**Test Failed**").append(" ");
        text.append("Failed: ").append(context.getFailedTests().size()).append(" ");
        text.append("Passed: ").append(context.getPassedTests().size()).append(" ");
        text.append("Skipped: ").append(context.getSkippedTests().size()).append("\n");

        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "result.txt")) {
            byte[] buffer = text.toString().getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        log.info(System.getProperty("user.dir") + File.separator + "result.txt путь где сохранен результат тестов.");
    }
}
