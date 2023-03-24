package org.example.model.enums;

public enum Mouvement {
    D("DROITE"),
    G("GAUCHE"),
    A("AVANCER");

    private final String value;

    Mouvement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
