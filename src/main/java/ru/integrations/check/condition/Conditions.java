package ru.integrations.check.condition;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;

@Slf4j
@UtilityClass
public class Conditions {
    /**
     * Check statusCode response.
     *
     * @param statusCode Integer expected status code.
     * @return StatusCodeCondition
     */
    @Step("Check status code response, expected {statusCode}")
    public static StatusCodeCondition statusCode(int statusCode) {
        log.info("Response equals status code: {}", statusCode);
        return new StatusCodeCondition(statusCode);
    }

    /**
     * Check value with jsonPath for equals condition.
     *
     * @param jsonPath String jsonPath.
     * @param matcher  Matcher condition.
     * @return BodyJsonCondition
     */
    @SuppressWarnings("rawtypes")
    @Step("Check value in response with: {jsonPath} condition: {matcher}")
    public static BodyJsonCondition bodyField(String jsonPath, Matcher matcher) {
        log.info("Value in response: {} equals to: {}", jsonPath, matcher);
        return new BodyJsonCondition(jsonPath, matcher);
    }

    /**
     * Check html response for equals condition.
     *
     * @param matcher Matcher condition.
     * @return BodyHtmlCondition
     */
    @SuppressWarnings("rawtypes")
    @Step("Check response condition: {matcher}")
    public static BodyHtmlCondition bodyField(Matcher matcher) {
        log.info("Response equals: {}", matcher);
        return new BodyHtmlCondition(matcher);
    }

    /**
     * Check header value for equal condition.
     *
     * @param headerValue header key.
     * @param matcher condition.
     * @return HeaderCondition
     */
    @SuppressWarnings("rawtypes")
    @Step("Check header value: {headerValue} condition: {matcher}")
    public static HeaderCondition headerField(String headerValue, Matcher matcher) {
        log.info("Value: {} equals in response: {}", headerValue, matcher);
        return new HeaderCondition(headerValue, matcher);
    }
}
