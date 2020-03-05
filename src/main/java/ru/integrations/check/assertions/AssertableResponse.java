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

    public AssertableResponse(Response response, CheckBodyField... body) {
        this.response = response;
        checkAll(body);
    }

    public AssertableResponse(Response response, Integer status, CheckBodyField... body) {
        this.response = response;
        shouldHave(statusCode(status));
        checkAll(body);
    }

    /**
     * Проверить содержит ли запрос искомое значение.
     *
     * @param conditions Тип проверки.
     * @return AssertableResponse
     */
    public AssertableResponse shouldHave(Condition conditions) {
        conditions.check(response);
        return this;
    }

    /**
     * Извлечь строку по JsonPath.
     *
     * @param jsonPath путь к атрибуту.
     * @return String
     */
    public <T> T getJsonPathValue(String jsonPath) {
        log.info("Извлчение значения из тела ответа по JsonPath {}", jsonPath);
        return response
                .then()
                .extract()
                .jsonPath()
                .get(jsonPath);
    }

    /**
     * Извлчечь значение по HtmlPath.
     *
     * @param htmlPath путь к значению.
     * @return String
     */
    public String getHtmlPathValue(String htmlPath) {
        log.info("Извлчение значения из тела ответа по HtmlPath {}", htmlPath);
        return response
                .then()
                .extract()
                .htmlPath()
                .getString(htmlPath).replace("\"", "");
    }

    /**
     * Извлечение параметра из заголовка ответа
     * @param headerValue имя ключа
     * @return Значение.
     */
    public String getHeaderValue(String headerValue) {
        log.info("Извлчение значения из заголовка ответа {}", headerValue);
        return response
                .then()
                .extract()
                .header(headerValue);
    }

    /**
     * Извлечь Json в Java-Класс.
     *
     * @param tClass Class.class в который нужно извлечь ответ.
     * @param <T>    Тип возвращаемого класса.
     * @return TClass
     */
    public <T> T asPOJO(Class<T> tClass) {
        return response
                .then()
                .extract()
                .as(tClass);
    }

    public Integer getStatusCode() {
        return response
                .getStatusCode();
    }

    public ExtractableResponse<Response> execute() {
        return response
                .then()
                .extract();
    }

    public void checkAll(CheckBodyField... body) {
        Arrays.asList(body).forEach(value -> shouldHave((Condition) value.bodyField()));
    }
}
