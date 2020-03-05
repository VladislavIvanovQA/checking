package ru.integrations.check.condition;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

@RequiredArgsConstructor
public class HeaderCondition implements Condition {
    private final String headerValue;
    @SuppressWarnings("rawtypes")
    private final Matcher matcher;

    @Override
    public void check(Response response) {
        response
                .then()
                .header(headerValue, matcher);
    }

    @Override
    public String toString() {
        return "HeaderCondition{" +
                "headerValue='" + headerValue + '\'' +
                ", matcher=" + matcher +
                '}';
    }
}
