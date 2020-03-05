package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Проверка statusCode.
 */
@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {
    private final int expectedStatusCode;

    /**
     * Проверка на соответствие statusCode на полученный из ответа.
     */
    @Override
    public void check(Response response) {
        response.then()
                .statusCode(expectedStatusCode);
    }
}
