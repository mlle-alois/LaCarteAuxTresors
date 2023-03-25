package org.example.model;

import org.example.model.enums.Mouvement;
import org.example.model.enums.Orientation;
import org.example.parser.exceptions.InvalidInputFileFormatException;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.GameUtils.STEP;

public class Aventurier extends Element {

    private final String nom;
    private Orientation orientation;
    private final List<Mouvement> mouvements;
    private int nbTresorRamasse;

    public Aventurier(int axeHorizontal, int axeVertical, String nom, Orientation orientation, String mouvements) {
        super(axeHorizontal, axeVertical);
        this.nom = nom;
        this.orientation = orientation;
        this.mouvements = initMouvements(mouvements);
        this.nbTresorRamasse = 0;
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

    public int getNbTresorRamasse() {
        return nbTresorRamasse;
    }

    private List<Mouvement> initMouvements(String mouvementsString) {
        List<Mouvement> mouvements = new ArrayList<>();
        try {
            for (char mouvement : mouvementsString.toCharArray()) {
                mouvements.add(Mouvement.valueOf(String.valueOf(mouvement)));
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputFileFormatException(mouvementsString);
        }
        return mouvements;
    }
    public boolean jouer() {
        if (mouvements.isEmpty()) {
            return false;
        }
        Mouvement mouvement = mouvements.remove(0);
        switch (mouvement) {
            case A -> avancer();
            case D -> tournerADroite();
            case G -> tournerAGauche();
        }
        return true;
    }

    private void avancer() {
        int newAxeVertical;
        int newAxeHorizontal;
        switch (orientation) {
            case S -> {
                newAxeVertical = getAxeVertical() + STEP;
                newAxeHorizontal = getAxeHorizontal();
            }
            case E -> {
                newAxeVertical = getAxeVertical();
                newAxeHorizontal = getAxeHorizontal() + STEP;
            }
            case O -> {
                newAxeVertical = getAxeVertical();
                newAxeHorizontal = getAxeHorizontal() - STEP;
            }
            default -> {
                newAxeVertical = getAxeVertical() - STEP;
                newAxeHorizontal = getAxeHorizontal();
            }
        }
        if (Carte.isNewPositionAvailable(newAxeHorizontal, newAxeVertical)) {
            setAxeVertical(newAxeVertical);
            setAxeHorizontal(newAxeHorizontal);
            if (Carte.isTresorOnThisPosition(newAxeHorizontal, newAxeVertical)) {
                ramasserTresor(newAxeHorizontal, newAxeVertical);
            }
        }
    }

    private void tournerAGauche() {
        switch (orientation) {
            case N -> setOrientation(Orientation.O);
            case S -> setOrientation(Orientation.E);
            case E -> setOrientation(Orientation.N);
            case O -> setOrientation(Orientation.S);
        }
    }

    private void tournerADroite() {
        switch (orientation) {
            case N -> setOrientation(Orientation.E);
            case S -> setOrientation(Orientation.O);
            case E -> setOrientation(Orientation.S);
            case O -> setOrientation(Orientation.N);
        }
    }

    private void ramasserTresor(int newAxeHorizontal, int newAxeVertical) {
        this.nbTresorRamasse++;
        Carte.ramasserTresors(newAxeHorizontal, newAxeVertical);
    }
}
