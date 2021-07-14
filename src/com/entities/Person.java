package com.entities;

import com.abstractions.FieldName;

public class Person {

    @FieldName("Nickname")
    private String nickName;

    @FieldName("PhoneNumber")
    private long phoneNumber;

    public String getNickName() {
        return nickName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Person(String nickName, long phoneNumber) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickName='" + nickName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public Person() {
        this.nickName = "";
        this.phoneNumber = 0L;
    }
}
