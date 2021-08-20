package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

/**
 * Class for execute with HtmlPath.
 */
@RequiredArgsConstructor
public class BodyHtmlCondition implements Condition {
    @SuppressWarnings("rawtypes")
    private final Matcher htmlPath;

    /**
     * Get value in htmlPath.
     *
     * @param response Response.
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
