package ru.integrations.check.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class SaveResult {
    public static String template = "<!DOCTYPE html>\n" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"ru\">\n" +
            "<body style=\"align-content: center\">\n" +
            "<div>\n" +
            "    <div>Доброго времени суток</div><br>\n" +
            "    <br>\n" +
            "    <div>Прошло тестирование ${PROJECT_NAME} автотестами.</div>\n" +
            "    <br>\n" +
            "    <div>Группа тестов: ${GROUP}</div>\n" +
            "    <div>Стенд: ${URL}</div>\n" +
            "    <br>\n" +
            "    <div>Результат тестирования: ${RESULT}</div>\n" +
            "    <div>Успешно: ${SUCCESSES_COUNT}</div>\n" +
            "    <div>Ошибки: ${ERROR_COUNT}</div>\n" +
            "    <div>Пропущенно: ${SKIP_COUNT}</div>\n" +
            "    <br>\n" +
            "    <b><a href=\"${RELEASE_URL}\">Сборка релиза в TFS</a></b><br>\n" +
            "    <b><a href=\"${REPORT_URL}\">Отчет тестирования</a></b>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

    public static void generateFile(ITestContext context,
                                    String projectName,
                                    String url,
                                    String releaseURL,
                                    String reportURL) {
        if (projectName != null && !projectName.isEmpty()) {
            template = template.replace("${PROJECT_NAME}", projectName);
        }

        String[] groups = context.getIncludedGroups();
        if (groups.length != 0) {
            if (groups.length > 1) {
                for (String group : groups) {
                    template = template.replace("${GROUP}", group);
                }
            } else {
                template = template.replace("${GROUP}", groups[0]);
            }
        } else {
            template = template.replace("${GROUP}", "Not Group");
        }
        if (url != null && !url.isEmpty()) {
            template = template.replace("${URL}", url);
        }
        if (context.getFailedTests().size() == 0 && context.getSkippedTests().size() == 0){
            template = template.replace("${RESULT}", "Test Passed");
        } else {
            template = template.replace("${RESULT}", "Test Failed");
        }
        template = template.replace("${SUCCESSES_COUNT}", String.valueOf(context.getPassedTests().size()));
        template = template.replace("${ERROR_COUNT}", String.valueOf(context.getFailedTests().size()));
        template = template.replace("${SKIP_COUNT}", String.valueOf(context.getSkippedTests().size()));

        template = template.replace("${RELEASE_URL}", releaseURL);
        template = template.replace("${REPORT_URL}", reportURL);

        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "result.txt")) {
            byte[] buffer = template.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        log.info(System.getProperty("user.dir") + File.separator + "result.txt путь где сохранен результат тестов.");
    }
}
