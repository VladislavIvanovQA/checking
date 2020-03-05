package ru.integrations.check.allureCustomFields;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface YouTrackIssues {
    YouTrackIssue[] value();

}
