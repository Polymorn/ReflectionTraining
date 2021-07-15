package com.entities;

import com.abstractions.FieldName;

public class Auto {

    private String color;

    private String id;

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Auto(String color, String id) {
        this.color = color;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "color='" + color + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Auto() {

    }
}
