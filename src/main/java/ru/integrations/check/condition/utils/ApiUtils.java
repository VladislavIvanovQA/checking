package ru.integrations.check.condition.utils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ApiUtils {
    public static Map<String, Object> extractFilter(Object filters) {
        Class<?> classes = filters.getClass();
        List<Field> methods = Arrays.asList(classes.getDeclaredFields());
        List<String> keys = methods.stream()
                .map(Field::getName)
                .collect(Collectors.toList());

        return getStringObjectMap(filters, classes, keys);
    }

    public static Map<String, Object> checkValidateFields(Object obj, String... fields) {
        Class<?> classes = obj.getClass();
        Field[] methods = classes.getDeclaredFields();

        List<String> keys = new ArrayList<>();
        for (Field method : methods) {
            String name = method.getName();
            if (fields != null && fields.length > 0) {
                if (!Arrays.asList(fields).contains(name)) {
                    keys.add(name);
                }
            } else {
                keys.add(name);
            }
        }

        return getStringObjectMap(obj, classes, keys);
    }

    private static List<Object> getObjects(Object filters, Class<?> classes, List<String> keys) {
        return keys.stream()
                .map(f -> {
                    Object value = null;
                    try {
                        Field field = classes.getDeclaredField(f);
                        field.setAccessible(true);
                        value = field.get(filters);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return value;
                })
                .collect(Collectors.toList());
    }

    private static Map<String, Object> getStringObjectMap(Object filters, Class<?> classes, List<String> keys) {
        List<Object> values = getObjects(filters, classes, keys);

        return IntStream.range(0, keys.size())
                .collect(
                        HashMap::new,
                        (m, i) -> {
                            if (values.get(i) != null && !values.get(i).equals("")) {
                                m.put(keys.get(i), values.get(i));
                            }
                        },
                        Map::putAll
                );
    }
}
