package com;

import com.entities.Auto;
import com.entities.House;
import com.entities.Person;
import com.parser.Parser;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        Parser parser = new Parser();

        Person person1 = new Person("Dumbass", 8_800_555_35_35L);
        Auto auto1 = new Auto("Red", "N_314_PI");
        House house = new House(123, "Greenfield");

        System.out.println(parser.serialize(person1));
        System.out.println(parser.serialize(auto1));
        System.out.println(parser.serialize(house));

        String jsonText1 = "{\n" +
                "\t\"Nickname\": \"Rolland\",\n" +
                "\t\"Phone Number\": 4342341\n" +
                "}";

        Person person2 = parser.deserialize(jsonText1, Person.class);
    }
}
