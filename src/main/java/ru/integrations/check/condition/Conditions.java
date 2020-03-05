package ru.integrations.check.condition;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;

/**
 * Класс помошник, для простоты обращения к проверкам.
 */
@Slf4j
@UtilityClass
public class Conditions {
    /**
     * Проверка statusCode ответа.
     *
     * @param statusCode int 200 код ответа.
     * @return StatusCodeCondition
     */
    @Step("Проверка кода ответа {statusCode}")
    public static StatusCodeCondition statusCode(int statusCode) {
        log.info("Ответ содержит код ответа {}", statusCode);
        return new StatusCodeCondition(statusCode);
    }

    /**
     * Проверка значения jsonPath на соотвествие matcher.
     *
     * @param jsonPath String jsonPath к элементу.
     * @param matcher  Matcher условие проверки.
     * @return BodyJsonCondition
     */
    @SuppressWarnings("rawtypes")
    @Step("Проверка значения в теле ответа {jsonPath} на соотвествие {matcher}")
    public static BodyJsonCondition bodyField(String jsonPath, Matcher matcher) {
        log.info("Значение в теле ответа {} соотвествует ожидаемому {}", jsonPath, matcher);
        return new BodyJsonCondition(jsonPath, matcher);
    }

    /**
     * Проверка на соотвествие matcher.
     *
     * @param matcher Matcher условие проверки.
     * @return BodyHtmlCondition
     */
    @SuppressWarnings("rawtypes")
    @Step("Проверка на соотвествие {matcher}")
    public static BodyHtmlCondition bodyField(Matcher matcher) {
        log.info("Ответ содержит {}", matcher);
        return new BodyHtmlCondition(matcher);
    }
    @SuppressWarnings("rawtypes")
    @Step("Проверка значения в заголовке ответа {headerValue} на соотвествие {matcher}")
    public static HeaderCondition headerField(String headerValue, Matcher matcher) {
        log.info("Значение {} в заголовке ответа ожидается {}", headerValue, matcher);
        return new HeaderCondition(headerValue, matcher);
    }
}
