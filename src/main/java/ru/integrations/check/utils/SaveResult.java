package ru.integrations.check.utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import ru.integrations.check.config.AllureConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SaveResult {
    private static final AllureUtils allure = new AllureUtils();
    private static AllureConfig config;

    public static void generateFile(ITestContext context, String projectName, String url, String releaseURLENV) {
        String launchId = null;
        try {
            launchId = allure.sendReportToAllure();
            config = allure.config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>();
        if (projectName != null && !projectName.isEmpty()) {
            result.put("PROJECT_NAME", projectName);
        }

        String[] groups = context.getIncludedGroups();
        if (groups.length != 0) {
            if (groups.length > 1) {
                for (String group : groups) {
                    result.put("GROUP", group);
                }
            } else {
                result.put("GROUP", groups[0]);
            }
        } else {
            result.put("GROUP", "Not Group");
        }
        result.put("URL", url);

        if (context.getFailedTests().size() == 0 && context.getSkippedTests().size() == 0) {
            result.put("RESULT", "Test Passed");
        } else {
            result.put("RESULT", "Test Failed");
        }
        result.put("SUCCESSES_COUNT", String.valueOf(context.getPassedTests().size()));
        result.put("ERROR_COUNT", String.valueOf(context.getFailedTests().size()));
        result.put("SKIP_COUNT", String.valueOf(context.getSkippedTests().size()));

        if (System.getenv(releaseURLENV) != null) {
            result.put("RELEASE_URL", System.getenv(releaseURLENV));
        }
        if (launchId != null) {
            result.put("REPORT_URL", config.ALLURE_ENDPOINT + "/launch/" + launchId);
        }

        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "result.txt")) {
            byte[] buffer = getResult(result).getBytes(StandardCharsets.UTF_8);
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        log.info("Save file in folder: {}", System.getProperty("user.dir") + File.separator + "result.txt");
    }

    public static String getResult(Map<String, String> payload) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("template.mustache");
        StringWriter writer = new StringWriter();
        mustache.execute(writer, payload).flush();
        log.info("Generate html");
        return writer.toString();
    }
}
