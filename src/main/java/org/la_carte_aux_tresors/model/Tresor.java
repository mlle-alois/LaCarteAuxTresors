package org.la_carte_aux_tresors.model;

public class Tresor extends Element {

    private int nombreTresorsRestants;
    public Tresor(int axeHorizontal, int axeVertical, int nombreTresorsRestants) {
        super(axeHorizontal, axeVertical);
        this.nombreTresorsRestants = nombreTresorsRestants;
    }

    public int getNombreTresorsRestants() {
        return nombreTresorsRestants;
    }

    public void ramasserTresor() {
        nombreTresorsRestants--;
    }
}
