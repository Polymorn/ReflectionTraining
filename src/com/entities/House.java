package com.entities;

import com.abstractions.FieldName;

public class House {

    @FieldName("House Number")
    private int id;

    @FieldName("Location")
    private String location;

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public House(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public House() {

    }
}
