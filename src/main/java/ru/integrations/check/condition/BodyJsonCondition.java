package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

/**
 * Класс для извлечения значения по JsonPath.
 */
@RequiredArgsConstructor
public class BodyJsonCondition implements Condition {
    private final String jsonPath;
    @SuppressWarnings("rawtypes")
    private final Matcher matcher;

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
