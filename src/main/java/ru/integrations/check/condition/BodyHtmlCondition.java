package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

/**
 * Класс для извлечения значения по HtmlPath.
 */
@RequiredArgsConstructor
public class BodyHtmlCondition implements Condition {
    @SuppressWarnings("rawtypes")
    private final Matcher htmlPath;

    /**
     * Получение значения по htmlPath.
     *
     * @param response Запрос.
     */
    @Override
    public void check(Response response) {
        response.then()
                .body(htmlPath);
    }

    @Override
    public String toString() {
        return "BodyHtmlCondition{" +
                "htmlPath=" + htmlPath +
                '}';
    }
}
