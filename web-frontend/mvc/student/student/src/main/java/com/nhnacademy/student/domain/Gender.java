package com.nhnacademy.student.domain;

public enum Gender {
    M("M"), F("F");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}