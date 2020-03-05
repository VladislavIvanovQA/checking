package ru.integrations.check.condition;

import io.restassured.response.Response;

/**
 * Интерфейс для создания метода проверки.
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
