package org.example.model.enums;

public enum Orientation {
    N("NORD"),
    S("SUD"),
    E("EST"),
    O("OUEST");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
