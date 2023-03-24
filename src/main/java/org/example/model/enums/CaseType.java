package org.example.model.enums;

public enum CaseType {
    PLAINE("."),
    MONTAGNE("M"),
    TRESOR("T"),
    AVENTURIER("A");

    private final String symbol;

    CaseType(String name) {
        this.symbol = name;
    }

    public String getSymbol() {
        return symbol;
    }
}
