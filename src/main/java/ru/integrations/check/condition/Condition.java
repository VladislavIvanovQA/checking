package ru.integrations.check.condition;

import io.restassured.response.Response;

/**
 * Interface create checking.
 */
public interface Condition {
    /**
     * Обязательность создания метода.
     * Реализация оставляется на дочек.
     *
     * @param response Запрос.
     */
    void check(Response response);
}
