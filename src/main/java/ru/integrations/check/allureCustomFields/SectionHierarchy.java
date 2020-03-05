package ru.integrations.check.allureCustomFields;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@LabelAnnotation(name = "section hierarchy")
public @interface SectionHierarchy {
    String value();
}
