package com;

import com.entities.Person;
import com.parser.Parser;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Parser parser = new Parser();
        Person person1 = new Person("Dumbass", 8_800_555_35_35L);

        System.out.println(parser.serialize(person1));

        String json = "{\n" +
                "\t\"Nickname\": \"Rolland\",\n" +
                "\t\"Phone Number\": 4342341\n" +
                "}";

        Person person2 = parser.deserialize(json, Person.class);

    }
}
