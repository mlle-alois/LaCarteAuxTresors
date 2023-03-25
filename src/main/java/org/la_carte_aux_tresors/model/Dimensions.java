package org.la_carte_aux_tresors.model;

public class Dimensions {

    private final int largeur;
    private final int hauteur;

    public Dimensions(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}
