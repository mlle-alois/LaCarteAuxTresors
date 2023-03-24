package org.example.model;

import org.example.model.enums.CaseType;

public class Element {

    private int axeHorizontal;
    private int axeVertical;
    private final CaseType elementType;

    public Element(int axeHorizontal, int axeVertical, CaseType elementType) {
        this.axeHorizontal = axeHorizontal;
        this.axeVertical = axeVertical;
        this.elementType = elementType;
    }

    public int getAxeHorizontal() {
        return axeHorizontal;
    }

    public int getAxeVertical() {
        return axeVertical;
    }

    public void setAxeHorizontal(int axeHorizontal) {
        this.axeHorizontal = axeHorizontal;
    }

    public CaseType getElementType() {
        return elementType;
    }

    public void setAxeVertical(int axeVertical) {
        this.axeVertical = axeVertical;
    }
}
