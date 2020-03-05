package ru.integrations.check.allureCustomFields;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(value = YouTrackIssues.class)
@LabelAnnotation(name = "youtrack")
public @interface YouTrackIssue {

    String value();
}
