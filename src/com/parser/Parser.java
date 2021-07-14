package com.parser;

import com.abstractions.FieldName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private final StringChecker stringChecker = new StringChecker();

    public <T> String serialize(T obj) throws IllegalAccessException {

        StringBuilder output = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            String str;
            fields[i].setAccessible(true);
            if (stringChecker.isNumeric(fields[i].get(obj).toString())) {
                str = "\n\t\"" + fields[i].getAnnotation(FieldName.class).value() + "\": " + fields[i].get(obj).toString();
            } else {
                str = "\n\t\"" + fields[i].getAnnotation(FieldName.class).value() + "\": " + "\"" + fields[i].get(obj).toString() + "\"";
            }
            if (i < fields.length - 1) {
                str += ",";
            }
            output.append(str);
        }

        output.append("\n}");
        return output.toString();

    }

    public<T> T deserialize(String text, Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Constructor<T> constructor = clazz.getConstructor();
        T cc = constructor.newInstance();

        HashMap<String, String> stringMap = new HashMap<>();
        String[] fieldsText = text.split(",");
        for (String fieldText : fieldsText) {
            fieldText = fieldText.replaceAll("[{}\" \n\t]", "");
            String[] str = fieldText.split(":");
            stringMap.put(str[0], str[1]);
        }

        Field[] fields = cc.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                if (entry.getKey().equals(field.getAnnotation(FieldName.class).value())) {
                    field.set(cc, entry.getValue());
                }
            }
        }

        System.out.println(cc);
        return cc;
    }
}