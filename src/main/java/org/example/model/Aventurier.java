package org.example.model;

import org.example.model.enums.CaseType;
import org.example.model.enums.Mouvement;
import org.example.model.enums.Orientation;

import java.util.List;

public class Aventurier extends Element {

    private String nom;
    private Orientation orientation;
    private final List<Mouvement> mouvements;

    public Aventurier(int axeHorizontal, int axeVertical, CaseType elementType, String nom, Orientation orientation, List<Mouvement> mouvements) {
        super(axeHorizontal, axeVertical, elementType);
        this.orientation = orientation;
        this.mouvements = mouvements;
    }

    public String getNom() {
        return nom;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void jouer() {
        if(mouvements.isEmpty()) {
            return;
        }
        Mouvement mouvement = mouvements.remove(0);
        switch (mouvement) {
            case A -> avancer();
            case D -> tournerADroite();
            case G -> tournerAGauche();
        }
    }

    public void avancer() {
        switch (orientation) {
            case N -> setAxeVertical(getAxeVertical() - 1);
            case S -> setAxeVertical(getAxeVertical() + 1);
            case E -> setAxeHorizontal(getAxeHorizontal() + 1);
            case O -> setAxeHorizontal(getAxeHorizontal() - 1);
        }
    }

    public void tournerAGauche() {
        switch (orientation) {
            case N -> setOrientation(Orientation.N);
            case S -> setOrientation(Orientation.S);
            case E -> setOrientation(Orientation.E);
            case O -> setOrientation(Orientation.O);
        }
    }

    public void tournerADroite() {
        switch (orientation) {
            case N -> setOrientation(Orientation.N);
            case S -> setOrientation(Orientation.S);
            case E -> setOrientation(Orientation.E);
            case O -> setOrientation(Orientation.O);
        }
    }
}
