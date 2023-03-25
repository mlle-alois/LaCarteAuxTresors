package org.example.model;

import org.example.model.enums.CaseType;

public class Tresor extends Element {

    private int nombreTresorsRestants;
    public Tresor(int axeHorizontal, int axeVertical, CaseType elementType, int nombreTresorsRestants) {
        super(axeHorizontal, axeVertical, elementType);
        this.nombreTresorsRestants = nombreTresorsRestants;
    }

    public int getNombreTresorsRestants() {
        return nombreTresorsRestants;
    }

    public void ramasserTresor() {
        nombreTresorsRestants--;
    }
}
