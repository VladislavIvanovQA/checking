package ru.integrations.check.allureCustomFields;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(TagsAllure.class)
@LabelAnnotation(name = "tag")
public @interface TagAllure {
    String value();
}
