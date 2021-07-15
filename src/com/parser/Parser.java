package com.parser;

import com.abstractions.FieldName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {

    private final StringChecker stringChecker = new StringChecker();

    public <T> String serialize(T obj) throws IllegalAccessException {

        StringBuilder output = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            String str;
            fields[i].setAccessible(true);
            if (stringChecker.isNumeric(fields[i].get(obj).toString())) {
                str = "\n\t\"" + getTextFromFieldOrAnnotation(fields[i]) + "\": " + fields[i].get(obj).toString();
            } else {
                str = "\n\t\"" + getTextFromFieldOrAnnotation(fields[i]) + "\": " + "\"" + fields[i].get(obj).toString() + "\"";
            }
            if (i < fields.length - 1) {
                str += ",";
            }
            output.append(str);
        }

        output.append("\n}");
        return output.toString();

    }

    public <T> T deserialize(String text, Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T obj = createNewObject(clazz);
        HashMap<String, String> stringMap = jsonTextSplit(text);

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                if (compareFieldWithText(field, entry.getKey()) && field.getType().equals(String.class)) {
                    field.set(obj, entry.getValue());
                } else if (compareFieldWithText(field, entry.getKey()) && field.getType().equals(long.class)) {
                    field.set(obj, Long.valueOf(entry.getValue()));
                } else if (compareFieldWithText(field, entry.getKey()) && field.getType().equals(int.class)) {
                    field.set(obj, Integer.valueOf(entry.getValue()));
                } else if (compareFieldWithText(field, entry.getKey()) && field.getType().equals(double.class)) {
                    field.set(obj, Double.valueOf(entry.getValue()));
                }
            }
        }
        System.out.println("Created new object: " + obj);
        return obj;
    }

    private HashMap<String, String> jsonTextSplit(String text) {
        HashMap<String, String> map = new HashMap<>();
        String[] fieldsText = text.split(",");
        for (String fieldText : fieldsText) {
            fieldText = fieldText.replaceAll("[{}\" \n\t]", "");
            String[] str = fieldText.split(":");
            map.put(str[0], str[1]);
        }
        return map;
    }

    private <T> T createNewObject(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    private boolean compareFieldWithText(Field field, String text) {
        if (Objects.isNull(field.getAnnotation(FieldName.class))) {
            return false;
        } else if (text.equals(field.getAnnotation(FieldName.class).value())) {
            return true;
        } else if (text.equals(field.getType().getName())) {
            return true;
        }
        return false;
    }

    private String getTextFromFieldOrAnnotation(Field field) {
        String text;
        if (!Objects.isNull(field.getAnnotation(FieldName.class))) {
            text = field.getAnnotation(FieldName.class).value();
        } else {
            text = field.getName();
        }
        return text;
    }

/*    private void fieldSetter(Field field, String text){
        Class<?> type = field.getType();
        type.
    }*/
}