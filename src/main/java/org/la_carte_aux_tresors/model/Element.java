package org.la_carte_aux_tresors.model;

public abstract class Element {

    private int axeHorizontal;
    private int axeVertical;

    public Element(int axeHorizontal, int axeVertical) {
        this.axeHorizontal = axeHorizontal;
        this.axeVertical = axeVertical;
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

    public void setAxeVertical(int axeVertical) {
        this.axeVertical = axeVertical;
    }
}
