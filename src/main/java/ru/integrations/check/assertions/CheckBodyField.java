package ru.integrations.check.assertions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hamcrest.Matcher;
import ru.integrations.check.condition.Conditions;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckBodyField {
    private String jsonPath;
    @SuppressWarnings("rawtypes")
    private Matcher matcher;

    @SuppressWarnings("rawtypes")
    public static CheckBodyField checkResponse(String jsonPath, Matcher matcher) {
        return new CheckBodyField(jsonPath, matcher);
    }

    public Object bodyField() {
        if (jsonPath.equals("")) {
            return Conditions.bodyField(matcher);
        } else {
            return Conditions.bodyField(jsonPath, matcher);
        }
    }
}
