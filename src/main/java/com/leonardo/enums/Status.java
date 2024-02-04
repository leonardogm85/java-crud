package com.leonardo.enums;

public enum Status {

    ACTIVE("active"),

    DEACTIVE("deactive");

    public String value;

    private Status(String value) {
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
