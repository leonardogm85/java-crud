package com.leonardo.enums;

public enum Category {

    BACK_END("backend"),

    FRONT_END("frontend");

    public String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
