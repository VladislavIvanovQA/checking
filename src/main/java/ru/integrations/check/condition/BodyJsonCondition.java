package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

/**
 * Class for execute with JsonPath.
 */
@RequiredArgsConstructor
public class BodyJsonCondition implements Condition {
    private final String jsonPath;
    @SuppressWarnings("rawtypes")
    private final Matcher matcher;

    /**
     * Get value in htmlPath.
     *
     * @param response Response.
     */
    @Override
    public void check(Response response) {
        response.then()
                .body(jsonPath, matcher);
    }

    @Override
    public String toString() {
        return "BodyJsonCondition{" +
                "jsonPath='" + jsonPath + '\'' +
                ", matcher=" + matcher +
                '}';
    }
}
