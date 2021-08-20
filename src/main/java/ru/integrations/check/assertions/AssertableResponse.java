package ru.integrations.check.assertions;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.integrations.check.condition.Condition;

import java.util.Arrays;

import static ru.integrations.check.condition.Conditions.statusCode;


@Slf4j
@RequiredArgsConstructor
public class AssertableResponse {
    private final Response response;

    public AssertableResponse(Response response, Integer status) {
        this.response = response;
        shouldHave(statusCode(status));
    }

    public AssertableResponse(Response response, CheckBodyField... checkBodyFields) {
        this.response = response;
        checkAll(checkBodyFields);
    }

    public AssertableResponse(Response response, Integer status, CheckBodyField... checkBodyFields) {
        this.response = response;
        shouldHave(statusCode(status));
        checkAll(checkBodyFields);
    }

    /**
     * Check response contains condition
     *
     * @param conditions Type check.
     * @return AssertableResponse
     */
    public AssertableResponse shouldHave(Condition conditions) {
        conditions.check(response);
        return this;
    }

    /**
     * Execute string with JsonPath.
     *
     * @param jsonPath path response.
     * @return String
     */
    public <T> T getJsonPathValue(String jsonPath) {
        log.info("Execute variable in body response with JsonPath {}", jsonPath);
        return response
                .then()
                .extract()
                .jsonPath()
                .get(jsonPath);
    }

    /**
     * Execute string with HtmlPath.
     *
     * @param htmlPath path response.
     * @return String
     */
    public String getHtmlPathValue(String htmlPath) {
        log.info("Execute variable in body response with HtmlPath {}", htmlPath);
        return response
                .then()
                .extract()
                .htmlPath()
                .getString(htmlPath).replace("\"", "");
    }

    /**
     * Execute string in header response
     *
     * @param headerValue key name
     * @return value.
     */
    public String getHeaderValue(String headerValue) {
        log.info("Execute string in header response {}", headerValue);
        return response
                .then()
                .extract()
                .header(headerValue);
    }

    /**
     * Execute Json in Java-object.
     *
     * @param tClass Object with returned.
     * @return tClass
     */
    public <T> T asPOJO(Class<T> tClass) {
        return response
                .then()
                .extract()
                .as(tClass);
    }

    /**
     * Get status code.
     *
     * @return status code.
     */
    public Integer getStatusCode() {
        return response
                .getStatusCode();
    }

    /**
     * Response to ExtractableResponse
     *
     * @return ExtractableResponse
     */
    public ExtractableResponse<Response> execute() {
        return response
                .then()
                .extract();
    }

    /**
     * Check may condition in response;
     * @param checkBodyFields
     */
    public void checkAll(CheckBodyField... checkBodyFields) {
        Arrays.asList(checkBodyFields).forEach(value -> shouldHave((Condition) value.bodyField()));
    }
}
