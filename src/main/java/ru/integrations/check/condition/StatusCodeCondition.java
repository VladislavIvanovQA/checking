package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Check statusCode.
 */
@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {
    private final int expectedStatusCode;

    /**
     * Check equals status code in status code in response.
     */
    @Override
    public void check(Response response) {
        response.then()
                .statusCode(expectedStatusCode);
    }
}
