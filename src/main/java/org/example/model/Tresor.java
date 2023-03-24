package org.example.model;

import org.example.model.enums.CaseType;

public class Tresor extends Element {

    private int nombreTresor;
    public Tresor(int axeHorizontal, int axeVertical, CaseType elementType, int nombreTresor) {
        super(axeHorizontal, axeVertical, elementType);
        this.nombreTresor = nombreTresor;
    }

    public int getNombreTresor() {
        return nombreTresor;
    }

    public void ramasserTresor() {
        nombreTresor--;
    }
}
