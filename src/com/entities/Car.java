package com.entities;

import com.abstractions.FieldName;

public class Car {

    @FieldName("Color")
    private String color;

    @FieldName("Car Number")
    private String id;

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Car(String color, String id) {
        this.color = color;
        this.id = id;
    }

    public Car() {

    }
}
