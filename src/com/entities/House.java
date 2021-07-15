package com.entities;

import com.abstractions.FieldName;

public class House {

    @FieldName("HouseNumber")
    private int id;

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

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }

    public House() {

    }
}
