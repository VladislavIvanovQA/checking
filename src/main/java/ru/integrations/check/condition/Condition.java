package ru.integrations.check.condition;

import io.restassured.response.Response;

public interface Condition {
    void check(Response response);
}
